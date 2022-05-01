package com.lionelrivas.demo.retry;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.util.concurrent.atomic.AtomicInteger;
import reactor.core.publisher.Flux;

public class RepeatDemo {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    public static void main(String[] args) {
        getIntegers()
                .repeat(() -> ATOMIC_INTEGER.get() < 14)
                .subscribe(new GenericSubscriber());
    }
    
    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnRequest(i -> System.out.println("doOnRequest()"))
                .doOnSubscribe(i -> System.out.println("doOnSubscribe()"))
                .doOnComplete(() -> System.out.println("doOnComplete()"))
                .map(i -> ATOMIC_INTEGER.getAndIncrement());
    }
}
