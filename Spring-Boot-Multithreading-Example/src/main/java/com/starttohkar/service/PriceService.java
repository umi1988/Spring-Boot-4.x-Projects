package com.starttohkar.service;

import com.starttohkar.entity.Price;

import java.util.Optional;

public interface PriceService {
    Price getPriceByProductId(Long productId);
}
