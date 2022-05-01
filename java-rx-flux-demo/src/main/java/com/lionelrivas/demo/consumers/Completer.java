package com.lionelrivas.demo.consumers;

public class Completer implements Runnable {

    @Override
    public void run() {
        System.out.println("Completed.");
    }

}
