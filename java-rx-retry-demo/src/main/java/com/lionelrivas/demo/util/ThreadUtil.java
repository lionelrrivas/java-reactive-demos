package com.lionelrivas.demo.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadUtil {
    
    public static void sleepSeconds(int seconds) {
        sleepMilliSeconds(seconds * 1000);
    }
    
    public static void sleepMilliSeconds(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
