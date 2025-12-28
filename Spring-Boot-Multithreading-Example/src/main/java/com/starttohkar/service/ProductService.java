package com.starttohkar.service;

import com.starttohkar.entity.Product;

import java.util.Optional;

public interface ProductService {
    Product findById(Long id);
}
