package com.lionelrivas.demo.flux;

import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import reactor.core.publisher.Flux;

public class StockPricePublisher {

    private static final Faker FAKER = Faker.instance();

    public Flux<Integer> publishPrice() {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> atomicInteger.accumulateAndGet(FAKER.random().nextInt(-5, 5), Integer::sum));
    }

}
