package com.lionelrivas.demo.threading;

import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnDemo {
   
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start Thread " + Thread.currentThread().getName());
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            IntStream.range(0, 5)
                    .forEach(i -> fluxSink.next(i));
            fluxSink.complete();
        }).doOnNext(i -> printThreadName("next " + i));
         
        flux.publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next " + i))
                .publishOn(Schedulers.parallel())
                .subscribe(subscriber -> printThreadName("subscriber " + 1));
        
        Thread.sleep(5000);
        System.out.println("end Thread " + Thread.currentThread().getName());
                
    }
    
    private static void printThreadName(String message) {
        System.out.println(message + " Thread " + Thread.currentThread().getName());
    }
}
