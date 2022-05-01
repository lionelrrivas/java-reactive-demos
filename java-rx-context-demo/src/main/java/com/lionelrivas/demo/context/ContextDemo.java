package com.lionelrivas.demo.context;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextDemo {

    public static void main(String[] args) {
        ContextDemo contextDemo = new ContextDemo();
        contextDemo.getWelcomeMessage()
                .contextWrite(contextModifier -> contextModifier.put("user", contextModifier.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "lionel"))
                .subscribe(new GenericSubscriber());
    }

    public Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Welcome " + contextView.get("user"));
            } else {
                return Mono.error(new RuntimeException("user unauthenticated"));
            }
        });
    }

}
