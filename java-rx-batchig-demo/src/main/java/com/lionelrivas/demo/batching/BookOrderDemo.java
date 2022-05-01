package com.lionelrivas.demo.batching;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import com.lionelrivas.demo.util.ThreadUtil;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BookOrderDemo {

    private static final Set<String> ALLOWED_CATEGORIES = Set.of("Science fiction", "Fantasy", "Suspense/Thriller");

    public static void main(String[] args) {
        BookOrderService bookOrderService = new BookOrderService();
        bookOrderService.bookOrderStream()
                .filter(bookOrder -> ALLOWED_CATEGORIES.contains(bookOrder.getCategory()))
                .buffer(Duration.ofSeconds(20))
                .map(BookOrderDemo::revenueReport)
                .subscribe(new GenericSubscriber());
        
        ThreadUtil.sleepSeconds(120);
    }

    private static RevenueReport revenueReport(List<BookOrder> bookOrders) {
        Map<String, Double> map = bookOrders.stream()
                .collect(Collectors.groupingBy(BookOrder::getCategory,
                        Collectors.summingDouble(BookOrder::getPrice)));
        return new RevenueReport(map);

    }
}
