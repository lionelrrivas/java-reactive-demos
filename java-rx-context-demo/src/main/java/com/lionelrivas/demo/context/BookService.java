package com.lionelrivas.demo.context;

import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class BookService {

    private static final Faker FAKER = Faker.instance();
    private static final Map<String, Integer> BOOKS_ALLOWED_FOR_CATEGORY = new HashMap<>();
    
    static {
        BOOKS_ALLOWED_FOR_CATEGORY.put("prime", 3);
        BOOKS_ALLOWED_FOR_CATEGORY.put("standard", 2);
    }

    public Mono<String> getBook() {
        return Mono.deferContextual(contextView -> {
            if (contextView.get("allow")) {
                return Mono.just(FAKER.book().title());
            } else {
                return Mono.error(new RuntimeException("not allowed"));
            }

        }).contextWrite(categoryContextModifier());
    }

    private static Function<Context, Context> categoryContextModifier() {
        return context -> {
            if (context.hasKey("category")) {
                String category = context.get("category").toString();
                Integer booksAllowed = BOOKS_ALLOWED_FOR_CATEGORY.get(category);
                if (booksAllowed > 0) {
                    BOOKS_ALLOWED_FOR_CATEGORY.put(category, booksAllowed - 1);
                    return context.put("allow", true);
                }
            }
            return context.put("allow", false);
        };
    }
}
