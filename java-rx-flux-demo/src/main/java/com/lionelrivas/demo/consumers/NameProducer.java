package com.lionelrivas.demo.consumers;

import com.github.javafaker.Faker;
import java.util.function.Consumer;
import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink; 
    private static final Faker FAKER = Faker.instance();
    
    @Override
    public void accept(FluxSink<String> fluxSink) {
        System.out.println("accept(): fluxSink = " + fluxSink);
        this.fluxSink = fluxSink;
    }
    
    public void produceName() {
        fluxSink.next(Thread.currentThread().getName() + " : " + FAKER.name().fullName());
    }
}
