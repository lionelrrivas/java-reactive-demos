package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.model.User;
import reactor.core.publisher.Flux;

public class UserService {

    public Flux<User> getUsers() {
        return Flux
                .range(1, 2)
                .map(userId -> new User(userId));
    }
}
