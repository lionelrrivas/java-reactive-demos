package com.lionelrivas.demo.batching;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class PurchaseOrder {
    
    private static final Faker FAKER = Faker.instance();
    private String item;
    private String category;
    private double price;
    private int quantity;
    
    public PurchaseOrder() {
        this.category = FAKER.commerce().department();
        this.item = FAKER.commerce().productName();
        this.quantity = FAKER.random().nextInt(1, 10);
        this.price = Double.valueOf(FAKER.commerce().price());
    }
}