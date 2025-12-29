package com.starttohkar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.starttohkar.service.ProductOrderService;
import com.starttohkar.service.ProductOrderServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    private final ProductOrderServiceV2 productOrderServiceV2;

    public ProductOrderController(ProductOrderService productOrderService,ProductOrderServiceV2 productOrderServiceV2) {
        this.productOrderService = productOrderService;
        this.productOrderServiceV2 =productOrderServiceV2;
    }

    //this endpoint for testing
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getIds() {
        return ResponseEntity.ok(productOrderService.getProductIds());
    }

    //this endpoint for data reset
    @PostMapping("/reset")
    public ResponseEntity<String> resetProductRecords() {
        String response = productOrderService.resetRecord();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processProductIds(@RequestBody List<Long> productIds)  {
        productOrderService.processProductIds(productIds);
        return ResponseEntity.ok("Products processed and events published.");
    }

    @PostMapping("/process/v2")
    public ResponseEntity<String> processProductIdsV2(@RequestBody List<Long> productIds) {
        productOrderServiceV2.executeProductIds(productIds);
        return ResponseEntity.ok("Products processed and events published.");
    }
}
