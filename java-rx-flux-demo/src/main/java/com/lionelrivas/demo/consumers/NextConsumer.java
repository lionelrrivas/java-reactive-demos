package com.lionelrivas.demo.consumers;

import java.util.function.Consumer;

public class NextConsumer implements Consumer<Object> {

    @Override
    public void accept(Object object) {
        System.out.println("onNext(): recieved  " + object);
    }

}
