package com.lionelrivas.demo.flux;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.consumers.NextConsumer;
import reactor.core.publisher.Flux;

public class RangeDemo {

    public static void main(String[] args) {
        Flux.range(0, 10)
                .log()
                .map(i -> Faker.instance().name().fullName())
                .log()
                .subscribe(new NextConsumer());
    }
}
