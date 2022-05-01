package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class BufferDemo {

    public static void main(String[] args) {
        
        eventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))  
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(10);
    } 
    
    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> "event" + i);
    }
}
