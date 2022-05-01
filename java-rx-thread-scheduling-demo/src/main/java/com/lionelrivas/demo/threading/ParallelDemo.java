package com.lionelrivas.demo.threading;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelDemo {
    
        public static void main(String[] args) throws InterruptedException {
            
            Flux.range(0, 30)
                    .parallel()
                    .runOn(Schedulers.boundedElastic())
                    .doOnNext(i -> printThreadName("next " + i))
                    .subscribe(i -> printThreadName("subscribe " + i));
        
        Thread.sleep(5000);
    }
    
    private static void printThreadName(String message) {
        System.out.println(message + " Thread " + Thread.currentThread().getName());
    }

}
