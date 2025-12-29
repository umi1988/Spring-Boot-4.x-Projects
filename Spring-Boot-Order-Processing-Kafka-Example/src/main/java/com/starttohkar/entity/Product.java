package com.starttohkar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String category;
    private double price;
    @Column(name = "isOfferApplied")
    private boolean isOfferApplied;
    @Column(name = "discountPercentage")
    private double discountPercentage;
    @Column(name = "priceAfterDiscount")
    private double priceAfterDiscount;

    public Product() {
    }

    public Product(Long id, String name, String category, double price, boolean isOfferApplied, double discountPercentage, double priceAfterDiscount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isOfferApplied = isOfferApplied;
        this.discountPercentage = discountPercentage;
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOfferApplied() {
        return isOfferApplied;
    }

    public void setOfferApplied(boolean offerApplied) {
        isOfferApplied = offerApplied;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }
}
