package com.lionelrivas.demo.flux;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class FluxGenerationDemo {

    private static final Faker FAKER = Faker.instance();

    public static void main(String[] args) {

        Flux.generate(() -> 0, (counter, synchronousSink) -> {
                    String country = FAKER.country().name();
                    System.out.println("emitting " + country + ", counter " + counter);
                    
                    synchronousSink.next(country);
                    if ("canada".equalsIgnoreCase(country) || counter >= 10 ){
                        synchronousSink.complete();
                    }
                    return counter + 1;
                }).take(4)
                .subscribe(new GenericSubscriber());
    }
}
