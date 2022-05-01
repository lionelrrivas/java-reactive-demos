package com.lionelrivas.demo.sinks;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class UnicastSinkDemo {

    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        Flux<Object> flux = sink.asFlux();
        
        sink.tryEmitNext("hello!");
        sink.tryEmitNext("how are you");
        
        flux.subscribe(new GenericSubscriber("sam"));
        flux.subscribe(new GenericSubscriber("mike"));
        
        sink.tryEmitNext("?");
        
        flux.subscribe(new GenericSubscriber("jake"));
        
        sink.tryEmitNext("new message");
        
    }
}
