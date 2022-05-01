package com.lionelrivas.demo.sinks;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class AllOrNothingDemo {

    public static void main(String[] args) throws InterruptedException {
        
        System.setProperty("reactor.bufferSize.small", "16"); 
        
        /**
         * Ignore slow subscribers and emits the element to fast ones as a best effort. 
         * From the perspective of slow subscribers, data is dropped
         * and never seen, but they are not terminated
         */
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        
        /**
         * if any of the subscribers cannot process an element, 
         * failing fast and backing off from emitting the element at all
         */
        // Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        Flux<Object> flux = sink.asFlux();
        
        sink.tryEmitNext("hello!");
        sink.tryEmitNext("how are you");
        
        flux.subscribe(new GenericSubscriber("sam"));
        
        flux.delayElements(Duration.ofMillis(200))
                .subscribe(new GenericSubscriber("mike"));
        
        IntStream.rangeClosed(1, 1000)
                .forEach(sink::tryEmitNext);
        
        ThreadUtil.sleepSeconds(10);
        
        
        
    }
}
