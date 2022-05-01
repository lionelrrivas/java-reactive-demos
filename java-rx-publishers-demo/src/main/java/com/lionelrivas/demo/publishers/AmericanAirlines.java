package com.lionelrivas.demo.publishers;

import com.github.javafaker.Faker;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class AmericanAirlines {
    
    private static final Faker FAKER = Faker.instance();
    
    public Flux<String> getFlights() {
        return Flux.range(1, FAKER.random().nextInt(1, 10))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "American " + FAKER.random().nextInt(100, 999))
                .filter(i -> FAKER.random().nextBoolean());
    }

}
