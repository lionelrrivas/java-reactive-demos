package com.lionelrivas.demo.flux;

import com.lionelrivas.demo.consumers.NameProducer;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;

public class NameProducerDemo {
    
    public static void main(String[] args) throws InterruptedException {
        NameProducer nameProducer = new NameProducer();
        
        // create allows for multi-thread producers, thread-safe
        Flux.create(nameProducer)
                .subscribe(new GenericSubscriber());
        
        Runnable runnable = nameProducer::produceName;
        
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        IntStream.range(0, 10).forEach(i -> {
            executorService.execute(runnable);
        });
        
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        
    }
}
