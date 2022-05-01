package com.lionelrivas.demo.retry;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class AdvancedRetryDemo {

    private static final Faker FAKER = Faker.instance();

    public static void main(String[] args) {
        processOrder(FAKER.business().creditCardNumber())
                .retryWhen(
                        Retry.from(flux -> flux
                        .doOnNext(retrySignal -> {
                            System.out.println("doOnNext(): retries " + retrySignal.totalRetries());
                            System.out.println("doOnNext(): message " + retrySignal.failure().getMessage());
                        })
                        .handle((retrySignal, synchronousSink) -> {
                            if ("500".equals(retrySignal.failure().getMessage())) {
                                synchronousSink.next(1);
                            } else {
                                synchronousSink.error(retrySignal.failure());
                            }
                        }).delaySequence(Duration.ofSeconds(1)))
                )
                .subscribe(new GenericSubscriber());

        ThreadUtil.sleepSeconds(10);
    }

    private static Mono<String> processOrder(String creditCardNumber) {
        return Mono.fromSupplier(() -> {
            processPayment(creditCardNumber);
            return FAKER.idNumber().valid();
        });
    }

    private static void processPayment(String creditCardNumber) {
        int randomInt = FAKER.random().nextInt(1, 10);

        if (randomInt < 8) {
            throw new RuntimeException("500");
        } else if (randomInt < 10) {
            throw new RuntimeException("404");
        }
    }

}
