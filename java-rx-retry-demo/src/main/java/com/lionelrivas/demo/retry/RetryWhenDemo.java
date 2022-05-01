package com.lionelrivas.demo.retry;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryWhenDemo {

    private static final Faker FAKER = Faker.instance();
    
    public static void main(String[] args) {
        getIntegers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(20);
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
