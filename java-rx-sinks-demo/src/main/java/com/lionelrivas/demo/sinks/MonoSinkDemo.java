package com.lionelrivas.demo.sinks;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class MonoSinkDemo {

    public static void main(String[] args) {
        Sinks.One<String> sink = Sinks.one();

        Mono<String> mono = sink.asMono();
        mono.subscribe(new GenericSubscriber("sam"));
        mono.subscribe(new GenericSubscriber("mike"));
        sink.tryEmitValue("hello gents");
    }
}
