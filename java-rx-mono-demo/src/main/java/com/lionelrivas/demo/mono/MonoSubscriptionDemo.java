package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.Completer;
import com.lionelrivas.demo.consumers.ErrorConsumer;
import com.lionelrivas.demo.consumers.NextConsumer;
import reactor.core.publisher.Mono;

public class MonoSubscriptionDemo {

    public static void main(String[] args) {
        Mono<Integer> helloMono = Mono.just("hello")
                .map(String::length)
                .map(length -> length / 1);

        helloMono.subscribe(new NextConsumer(),
                new ErrorConsumer(),
                new Completer());
        
    }
}
