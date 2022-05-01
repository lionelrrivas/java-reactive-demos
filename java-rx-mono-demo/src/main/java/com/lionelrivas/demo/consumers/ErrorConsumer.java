package com.lionelrivas.demo.consumers;

import java.util.function.Consumer;

public class ErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable t) {
        System.out.println("onError(): " + t.getMessage());
    }

}
