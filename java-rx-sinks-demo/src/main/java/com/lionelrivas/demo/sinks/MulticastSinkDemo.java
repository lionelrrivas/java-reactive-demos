package com.lionelrivas.demo.sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class MulticastSinkDemo {

    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        
        List<Object> list = new ArrayList<>();
        
        flux.subscribe(list::add);
        
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        IntStream.rangeClosed(1, 1000)
                .forEach(i -> {
                    executorService.execute(() -> sink.emitNext(i, (signalType, emitResult) -> true));
                });
        
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(list.size());
        
    }
}
