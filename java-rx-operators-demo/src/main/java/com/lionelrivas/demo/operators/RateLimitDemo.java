package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class RateLimitDemo {

    public static void main(String[] args) {
        Flux.range(0, 25)
                .log()
                .limitRate(10)
                .subscribe(new GenericSubscriber());
    }
}
