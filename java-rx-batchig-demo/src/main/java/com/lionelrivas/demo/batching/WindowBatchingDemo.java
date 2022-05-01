package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WindowBatchingDemo {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    public static void main(String[] args) {

        eventStream()
                .window(Duration.ofSeconds(2))
                .flatMap(WindowBatchingDemo::eventBatcher)
                .subscribe(new GenericSubscriber());

        ThreadUtil.sleepSeconds(30);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "event" + i);
    }

    private static Mono<Integer> eventBatcher(Flux<String> flux) {
        return flux
                .doOnNext(event -> System.out.println("saving " + event))
                .doOnComplete(() -> {
                    System.out.println("batch saved");
                    System.out.println("------------------");
                })
                .then(Mono.just(ATOMIC_INTEGER.getAndIncrement()));
    }
}
