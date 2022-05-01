package com.lionelrivas.demo.mono;

import com.github.javafaker.Faker;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import reactor.core.publisher.Mono;

public class UserRepository {

    private static final Faker FAKER = Faker.instance();

    public Mono<User> findById(int id) {

        if (id == 1) {
            return Mono
                    .fromSupplier(userSupplier())
                    .flatMap(user -> {
                        user.setFirstName(user.getFirstName().toUpperCase());
                        return Mono.just(user);
                    });
        }

        return id == 2 ? Mono.empty() : Mono.error(errorSupplier("user " + id + " not found"));
    }
    
    public User randomUser() {
        return buildUser();
    }

    private User buildUser() {
        User user = new User();
        user.setFirstName(FAKER.name().firstName());
        user.setLastName(FAKER.name().lastName());

        return user;
    }

    private Supplier<Throwable> errorSupplier(String message) {
        return () -> new RuntimeException(message);
    }

    private Supplier<User> userSupplier() {
        System.out.println("setting up user supplier...");
        return () -> {
            sleep();
            return buildUser();
        };
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
