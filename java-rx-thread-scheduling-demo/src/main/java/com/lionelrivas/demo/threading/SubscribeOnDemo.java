package com.lionelrivas.demo.threading;

import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start Thread " + Thread.currentThread().getName());
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).subscribeOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("next " + i));
         
        Runnable runnable = () -> flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(subscriber -> printThreadName("subscriber " + subscriber));
        
        IntStream.range(0, 2)
                .forEach(i -> new Thread(runnable).start());
        
        Thread.sleep(5000);
        System.out.println("end Thread " + Thread.currentThread().getName());
                
    }
    
    private static void printThreadName(String message) {
        System.out.println(message + " Thread " + Thread.currentThread().getName());
    }
}
