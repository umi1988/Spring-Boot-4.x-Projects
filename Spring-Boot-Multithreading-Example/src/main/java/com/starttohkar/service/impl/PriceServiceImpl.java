package com.starttohkar.service.impl;

import com.starttohkar.entity.Price;
import com.starttohkar.repository.PriceRepository;
import com.starttohkar.service.PriceService;
import com.starttohkar.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class PriceServiceImpl implements PriceService {

    Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceByProductId(Long productId) {
        log.info("Getting price for the productId {}", productId);
        addDelay();
        return priceRepository.findByProductId(productId);
    }

    private void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
