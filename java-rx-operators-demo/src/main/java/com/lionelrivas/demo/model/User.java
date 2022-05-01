package com.lionelrivas.demo.model;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class User {
    private static final Faker FAKER = Faker.instance();
    
    private String name;
    private int userId;

    public User(int userId) {
        this.userId = userId;
        this.name = FAKER.name().fullName();
    }
    
}
