package com.lionelrivas.demo.operators;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class HandleDemo {

    private static final Faker FAKER = Faker.instance();

    public static void main(String[] args) {
        Flux.generate(synchronousSink -> synchronousSink.next(FAKER.country().name()))
                .map(Object::toString)
                .handle((country, synchronousSink) -> {
                    synchronousSink.next(country);
                    if ("canada".equalsIgnoreCase(country)) {
                        synchronousSink.complete();
                    }
                }).subscribe(new GenericSubscriber());
    }
}
