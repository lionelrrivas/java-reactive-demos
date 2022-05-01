package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.consumers.Completer;
import com.lionelrivas.demo.consumers.ErrorConsumer;
import com.lionelrivas.demo.consumers.NextConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConversionDemo {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("a");
        
        Flux<String> flux = Flux.from(mono);

        subscribe(flux);

        Mono<Integer> integerMono = Flux.range(0, 10)
                .filter(i -> i > 3)
                .next();
        
        subscribe(integerMono);

    }

    private static void subscribe(Flux<String> flux) {
        flux.subscribe(new NextConsumer(), 
                new ErrorConsumer(), 
                new Completer());
    }

    private static void subscribe(Mono<Integer> flux) {
        flux.subscribe(new NextConsumer(),
                new ErrorConsumer(),
                new Completer());
    }

}
