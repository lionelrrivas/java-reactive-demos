package com.lionelrivas.demo.overflow;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.util.ThreadUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class DropOverflowStrategyDemo {

    public static void main(String[] args) {

//      from Queues class
        List<Object> drops = new ArrayList<>();

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
                .onBackpressureDrop(drops::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    ThreadUtil.sleepMilliSeconds(10);
                })
                .log()
                .subscribe(new GenericSubscriber());

        ThreadUtil.sleepSeconds(15);
        System.out.println("drops = " + drops);
    }
}
