package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Flux;

public class TimeoutDemo {

    public static void main(String[] args) throws InterruptedException {

        getOrderNubmers()
                .timeout(Duration.ofSeconds(2), fallback())
                .subscribe(new GenericSubscriber());
        
        Thread.sleep(60000);

    }

    private static Flux<Integer> getOrderNubmers() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }
    
    private static Flux<Integer> fallback() {
        return Flux.range(1, 150)
                .delayElements(Duration.ofMillis(200));
    }
}
