package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class ConcatWithDemo {

    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> errorFlux = Flux.error(new RuntimeException("oops"));
        Flux<String> flux2 = Flux.just("c", "d", "e");
        
        Flux<String> flux = Flux.concatDelayError(flux1, errorFlux, flux2);
        flux.subscribe(new GenericSubscriber());
        
    }
}
