package com.lionelrivas.demo.batching;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookOrder {
    
    private static final Faker FAKER = Faker.instance();
    private final double price;
    private final String category;
    private final String title;
    private final String author;

    public BookOrder() {
        Book book = FAKER.book();
        this.category = book.genre();
        this.title = book.title();
        this.author = book.author();
        this.price = Double.valueOf(FAKER.commerce().price());
    }
}
