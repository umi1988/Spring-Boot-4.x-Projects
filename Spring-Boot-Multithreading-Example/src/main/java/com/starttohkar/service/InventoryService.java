package com.starttohkar.service;

import com.starttohkar.entity.Inventory;

import java.util.Optional;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
}