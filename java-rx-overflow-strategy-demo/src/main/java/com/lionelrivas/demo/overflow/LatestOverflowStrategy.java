package com.lionelrivas.demo.overflow;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.util.ThreadUtil;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class LatestOverflowStrategy {

    public static void main(String[] args) {

        // from Queues class
        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            IntStream.rangeClosed(1, 200)
                    .forEach(i -> {
                        if (!fluxSink.isCancelled()) {
                            fluxSink.next(i);
                            System.out.println("pushed " + i);
                            ThreadUtil.sleepMilliSeconds(1);
                        }
                    });
            fluxSink.complete();
        })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    ThreadUtil.sleepMilliSeconds(10);
                })
                .subscribe(new GenericSubscriber());

        ThreadUtil.sleepSeconds(6);
    }
}
