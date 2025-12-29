package com.starttohkar.service;

import com.starttohkar.entity.Product;
import com.starttohkar.repository.ProductOrderRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    //initialing the references with constructor injection
    public ProductOrderService(ProductOrderRepository productOrderRepository, KafkaTemplate kafkaTemplate, ObjectMapper mapper, @Value("${product.discount.update.topicName}") String topicName){
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

    private void fetchUpdateAndPublish(Long productId) {
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

    private void publishProductEvent(Product product)  {
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

    @SneakyThrows
    @Transactional
    public void processProductIds(List<Long> productsIds){
        productsIds.parallelStream().
                forEach(this::fetchUpdateAndPublish);
    }
}
