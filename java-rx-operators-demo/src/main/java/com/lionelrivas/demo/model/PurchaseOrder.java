package com.lionelrivas.demo.model;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class PurchaseOrder {
    private static final Faker FAKER = Faker.instance();
    
    private String price;
    private String item;
    private int userId;

    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item = FAKER.commerce().productName();
        this.price = FAKER.commerce().price();
    }
}
