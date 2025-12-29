package com.starttohkar.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.starttohkar.entity.Product;
import com.starttohkar.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceV2 {

    private final ProductOrderRepository productOrderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    //initialing the references with constructor injection
    public ProductOrderServiceV2(ProductOrderRepository productOrderRepository, KafkaTemplate kafkaTemplate, ObjectMapper mapper, @Value("${product.discount.update.topicName}") String topicName){
        this.productOrderRepository=productOrderRepository;
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=mapper;
        this.topicName=topicName;
    }

    public String resetRecord(){
        productOrderRepository.findAll()
                .forEach(product -> {
                    product.setOfferApplied(false);
                    product.setPriceAfterDiscount(product.getPrice());
                    product.setDiscountPercentage(0);
                    productOrderRepository.save(product);
                });
        return "Data Reset to DB";
    }

    public List<Long> getProductIds() {
        return productOrderRepository.findAll().
                stream().
                map(Product::getId).
                collect(Collectors.toList());
    }

    private void fetchUpdateAndPublish(Long productId)  {
        //fetch product by id
        Product product = productOrderRepository.findById(productId).
                orElseThrow(()->
                        new IllegalArgumentException("Product ID does not exist in the system")
                );

        //update discount properties
        updateDiscountedPrice(product);

        //save to DB
        productOrderRepository.save(product);

        //kafka events
        publishProductEvent(product);
    }

    private void publishProductEvent(Product product) {
        String productJson =  objectMapper.writeValueAsString(product);
        kafkaTemplate.send(topicName,productJson);
    }

    private void updateDiscountedPrice(Product product) {
        double price = product.getPrice();

        int discountPercentage = price >= 1000 ? 10 : (price > 500 ? 5 : 0);

        double priceAfterDiscount = price - (price * discountPercentage / 100);

        if(discountPercentage>0){
            product.setOfferApplied(true);
        }

        product.setDiscountPercentage(discountPercentage);
        product.setPriceAfterDiscount(priceAfterDiscount);
    }

//    @SneakyThrows
//    @Transactional
//    public void processProductIds(List<Long> productsIds) throws JsonProcessingException{
//        productsIds.parallelStream().
//                forEach(this::fetchUpdateAndPublish);
//    }

    public void processProductIds(List<Long> batches){
        System.out.println("Processing batch " + batches + " by thread " + Thread.currentThread().getName());
        batches.forEach(this::fetchUpdateAndPublish);
    }


    private List<List<Long>> splitIntoBatches(List<Long> productIds, int batchSize){
        int totalSize = productIds.size();
        int batchNumbs = (totalSize + batchSize - 1) / batchSize; // ~6.98 meaning 6
        List<List<Long>> batches = new ArrayList<>();
        for (int i =0; i < batchNumbs; i++){
            int start = i * batchSize;
            int end  = Math.min(totalSize,(i+1)*batchSize);
            batches.add(productIds.subList(start,end));
            System.out.println(start + "  ****  "+  end);
        }
        return batches;
    }

    public void executeProductIds(List<Long> productIds){
        List<List<Long>> batches = splitIntoBatches(productIds, 50);

        List<CompletableFuture<Void>> futures = batches.
                stream().
                map(batch -> CompletableFuture.runAsync(() -> {
                    processProductIds(batch);
                }, executorService)).
                collect(Collectors.toList());

        //wait for all future to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
