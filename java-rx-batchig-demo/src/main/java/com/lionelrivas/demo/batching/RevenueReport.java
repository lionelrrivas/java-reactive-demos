package com.lionelrivas.demo.batching;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.ToString;

@ToString
public class RevenueReport {

    private final LocalDateTime orderDate = LocalDateTime.now();
    private final Map<String, Double> revenueMap;

    public RevenueReport(Map<String, Double> revenueMap) {
        this.revenueMap = revenueMap;
    }
}
