package com.lionelrivas.demo.publishers;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class ZipDemo {
    
    public static void main(String[] args) {
        
        Flux.zip(getCarBody(), getCarEngine(), getCarTires())
                .subscribe(new GenericSubscriber());
        
    }
    
    private static Flux<String> getCarBody() {
        return Flux.range(1, 5)
                .map(i -> "body");
    }
    
    private static Flux<String> getCarEngine() {
        return Flux.range(1, 4)
                .map(i -> "engine");
    }
    
    private static Flux<String> getCarTires() {
        return Flux.range(1, 6)
                .map(i -> "tires");
    }

}
