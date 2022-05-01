package com.lionelrivas.demo.flux;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class FluxCreationDemo {
    
    private static final Faker FAKER = Faker.instance();
    
    public static void main(String[] args) {
        
        // only one instace of the fluxSink is produced.
        Flux.create(fluxSink -> {
            String country;
            while (!(country = FAKER.country().name()).toLowerCase().equals("canada") && !fluxSink.isCancelled()) {
                System.out.println(country);
                fluxSink.next(country);
            }
            fluxSink.complete();
            
        }).take(3)
                .subscribe(new GenericSubscriber());
    }

}
