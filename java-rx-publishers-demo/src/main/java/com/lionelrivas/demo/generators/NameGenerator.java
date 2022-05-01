package com.lionelrivas.demo.generators;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.util.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Flux;

public class NameGenerator {

    private static final Faker FAKER = Faker.instance();
    
    private static final List<String> nameCache = new ArrayList<>();
    
    public Flux<String> generateNames() {
        return Flux.generate((synchronousSink) -> {
            String name = FAKER.name().firstName();
            System.out.println(name + " from generator");
            synchronousSink.next(name);
            nameCache.add(name);
            ThreadUtil.sleepSeconds(1);
        })
                .cast(String.class)
                .startWith(namesFromCache());
    }
    
    public Flux<String> namesFromCache() {
        return Flux.fromIterable(nameCache);
    }
}
