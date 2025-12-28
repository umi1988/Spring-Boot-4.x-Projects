package com.starttohkar.facade;


import com.starttohkar.dto.ProductDetailDTO;
import com.starttohkar.entity.Inventory;
import com.starttohkar.entity.Price;
import com.starttohkar.entity.Product;
import com.starttohkar.service.InventoryService;
import com.starttohkar.service.PriceService;
import com.starttohkar.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSyncFacade {

    Logger log = LoggerFactory.getLogger(ProductSyncFacade.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PriceService priceService;


    public ProductDetailDTO getProductDetails(long productId) {
        log.info("Sync facade for getting product details for the product id {}",productId);

        //fetch product
        Product product = productService.findById(productId);

        //fetch the price
        Price price = priceService.getPriceByProductId(productId);

        //fetch the inventory
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        //combine result
        return new ProductDetailDTO(productId, product.getCategory().getName(),
                product.getName(), product.getDescription(),
                inventory.getAvailableQuantity(), price.getPrice(),
                inventory.getStatus());
    }

}
