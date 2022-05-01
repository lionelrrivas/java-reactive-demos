package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.time.Duration;
import reactor.core.publisher.Flux;

public class DelayElementsDemo {

    public static void main(String[] args) throws InterruptedException {

        // sets the buffer size from the default of 32
        // see reactor.util.concurrent.Queues
        System.setProperty("reactor.bufferSize.x", "9");

        Flux.range(0, 100)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(new GenericSubscriber());

        Thread.sleep(60000);
    }
}
