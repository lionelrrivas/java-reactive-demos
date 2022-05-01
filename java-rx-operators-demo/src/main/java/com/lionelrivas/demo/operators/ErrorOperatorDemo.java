package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorOperatorDemo {
    
    public static void main(String[] args) {
        Flux.range(0, 10)
                .log()
                .map(i -> 10 / (5 - i))
                .onErrorReturn(-1)
                .onErrorResume(e -> fallback())
                .onErrorContinue((error, element) -> System.out.println("onErrorContinue(): element " + element))
                .subscribe(new GenericSubscriber());
    }
    
    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> 150);
    }

}
