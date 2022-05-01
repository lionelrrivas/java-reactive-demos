package com.lionelrivas.demo.context;

import java.util.Map;
import static java.util.Map.entry;
import java.util.function.Function;
import reactor.util.context.Context;

public class UserService {

//    private static final Map<String, String> USER_MAP = Map.ofEntries(
//            entry("sam", "standard"),
//            entry("mike", "prime"));
    private static final Map<String, String> USER_MAP = Map.of("sam", "standard", "mike", "prime");

    public Function<Context, Context> contextCategoryModifier() {
        return context -> {
            String user = context.get("user").toString();
            String category = USER_MAP.get(user);
            return context.put("category", category);
        };
    }

}
