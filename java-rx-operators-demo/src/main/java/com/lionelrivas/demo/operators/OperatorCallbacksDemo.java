package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.util.stream.IntStream;
import reactor.core.publisher.Flux;

public class OperatorCallbacksDemo {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            System.out.println("create started...");
            IntStream.rangeClosed(1, 10)
                    .forEach(i -> fluxSink.next(i));
//            fluxSink.complete();
            fluxSink.error(new RuntimeException("error occurred."));
            System.out.println("create completed.");
        }).doOnComplete(() -> System.out.println("doOnComplete()"))
                .doFirst(() -> System.out.println("doFirst()"))
                .doOnNext(i -> System.out.println("doOnNext() " + i))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe() " + subscription))
                .doOnRequest(longConsumer -> System.out.println("doOnRequest() " + longConsumer))
                .doOnError(error -> System.out.println("doOnError()" + error))
                .doOnTerminate(() -> System.out.println("doOnTerminate()"))
                .doOnCancel(() -> System.out.println("doOnCancel()"))
                .doFinally(signalType -> System.out.println("doFinally() " + signalType))
                .doOnDiscard(Object.class, object -> System.out.println("doOnDiscard() " + object))
                .doFinally(signalType -> System.out.println("doFinally() " + signalType))
                .subscribe(new GenericSubscriber());
    }

}
