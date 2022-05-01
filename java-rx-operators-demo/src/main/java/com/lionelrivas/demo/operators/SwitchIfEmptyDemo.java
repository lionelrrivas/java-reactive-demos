package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class SwitchIfEmptyDemo {

    public static void main(String[] args) {
        getOrderNubmers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(new GenericSubscriber());
    }

    // call to retrieve from cache
    private static Flux<Integer> getOrderNubmers() {
        return Flux.range(1, 10);
    }

    // call to db on cache miss
    private static Flux<Integer> fallback() {
        return Flux.range(20, 5);
    }
}
