package com.lionelrivas.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadUtil {

    public static void sleepSeconds(int seconds) {
        sleepMilliSeconds(1000 * seconds);
    }

    public static void sleepMilliSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
