package com.lionelrivas.demo.sinks;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class ReplaySinkDemo {

    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> sink = Sinks.many().replay().all();

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
