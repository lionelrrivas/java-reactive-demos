package com.lionelrivas.demo.retry;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Flux;

public class RetryDemo {

    private static final Faker FAKER = Faker.instance();
    
    public static void main(String[] args) {
        getIntegers()
                .retry(2)
                .subscribe(new GenericSubscriber());
    }
    
    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnRequest(i -> System.out.println("doOnRequest()"))
                .doOnSubscribe(i -> System.out.println("doOnSubscribe()"))
                .doOnComplete(() -> System.out.println("doOnComplete()"))
                .map(i -> i / (FAKER.random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(i -> System.out.println("doOnError()"));
    }
}
