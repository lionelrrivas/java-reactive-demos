package com.lionelrivas.demo.publishers;

import com.github.javafaker.Faker;
import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class CarPriceDemo {

    private static final Faker FAKER = Faker.instance();

    private static final int INITIAL_CAR_PRICE = 10000;

    public static void main(String[] args) {
        Flux.combineLatest(monthStream(), demandFactorStream(), (month, demandFactor) -> {
            System.out.println("demandFactor = " + demandFactor);
            System.out.println("month = " + month);
            return INITIAL_CAR_PRICE - (month * 100) * (demandFactor);
        })
                .subscribe(new GenericSubscriber());

        ThreadUtil.sleepSeconds(20);
    }

    private static Flux<Long> monthStream() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
    }

    private static Flux<Double> demandFactorStream() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> FAKER.random().nextInt(80, 120) / 100d)
                .startWith(1d);
    }
}
