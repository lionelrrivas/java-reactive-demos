package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class BufferOverlapDemo {

    public static void main(String[] args) {
        
        eventStream()
                .buffer(3, 5)  
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(12);
    } 
    
    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> "event" + i);
    }
}
