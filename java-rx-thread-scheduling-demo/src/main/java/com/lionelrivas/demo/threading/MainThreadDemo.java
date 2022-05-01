package com.lionelrivas.demo.threading;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class MainThreadDemo {
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start Thread " + Thread.currentThread().getName());
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));
         
        Runnable runnable = () -> flux.subscribe(subscriber -> printThreadName("subscriber " + 1));
        IntStream.range(0, 2)
                .forEach(i -> new Thread(runnable).start());
        
        Thread.sleep(5000);
        System.out.println("end Thread " + Thread.currentThread().getName());
                
    }
    
    private static void printThreadName(String message) {
        System.out.println(message + " Thread " + Thread.currentThread().getName());
    }

}
