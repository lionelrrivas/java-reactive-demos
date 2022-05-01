package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class DefaultIfEmptyDemo {

    public static void main(String[] args) {
        getOrderNubmers()
                .filter(i -> i > 10)
                .defaultIfEmpty(-150)
                .subscribe(new GenericSubscriber());
    }
    
    private static Flux<Integer> getOrderNubmers() {
        return Flux.range(1, 10);
    }
}
