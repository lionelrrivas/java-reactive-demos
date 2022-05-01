package com.lionelrivas.demo.operators;

import com.lionelrivas.demo.subscribers.GenericSubscriber;
import java.util.Comparator;
import java.util.function.Function;
import reactor.core.publisher.Flux;

public class SwitchOnFirstDemo {
    public static void main(String[] args) {
        getPersons()
                .switchOnFirst((signal, personFlux) -> {
                    System.out.println("in switchOnFirst operator");
                    return signal.isOnNext()
                            && signal.get().getAge() > 10 ? personFlux : filterAndMap().apply(personFlux);
                })
                .subscribe(new GenericSubscriber());
    }

    private static Flux<Person> getPersons() {
        return Flux
                .range(1, 10)
                .map(i -> new Person());
    }
    
    private static Function<Flux<Person>, Flux<Person>> filterAndMap() {
        System.out.println("using filter and map");
        return flux -> flux
                .filter(person -> person.getAge() > 10)
                .sort(Comparator.comparing(Person::getName))
                .doOnDiscard(Person.class, person -> System.out.println("doOnDiscard() " + person))
                .doOnNext(person -> person.setName(person.getName().toUpperCase()));
    }
}
