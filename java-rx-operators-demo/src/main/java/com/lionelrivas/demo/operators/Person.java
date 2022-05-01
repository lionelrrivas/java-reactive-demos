package com.lionelrivas.demo.operators;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class Person {
    
    private static final Faker FAKER = Faker.instance();

    private String name;
    private int age;
    
    public Person() {
        this.name = FAKER.name().firstName();
        this.age = FAKER.random().nextInt(1, 30);
    }
}
