package com.lionelrivas.demo.mono;

import com.lionelrivas.demo.consumers.NextConsumer;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import reactor.core.publisher.Mono;

public class SupplierDemo {
    public static void main(String[] args) {
        
        Supplier<String> stringSupplier = () -> "lionel rivas";
        Mono<String> nameMono = Mono.fromSupplier(stringSupplier);
        nameMono.subscribe(new NextConsumer());
        
        Callable<String> stringCallable = () -> "lionel rivas";
        nameMono = Mono.fromCallable(stringCallable);
        nameMono.subscribe(new NextConsumer());
        
    }
}
