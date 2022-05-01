package com.lionelrivas.demo.context;

import com.lionelrivas.demo.model.BookOrder;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

class FluxTest {

    @Test
    void fluxCompletesAsExpected() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();

        StepVerifier.create(just)
                .expectNext(1, 2, 3).verifyComplete();
    }

    @Test
    void fluxCompletesWithError() {
        Flux<Integer> just = Flux.just(1, 2, 3);

        Flux<Integer> errorFlux = Flux.error(new RuntimeException("error"));

        Flux<Integer> concatenatedFlux = Flux.concat(just, errorFlux);
        StepVerifier.create(concatenatedFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyError();

        StepVerifier.create(concatenatedFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyError(RuntimeException.class);

        StepVerifier.create(concatenatedFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyErrorMessage("error");

    }

    @Test
    void fluxRangeWithExpectedCount() {
        Flux<Integer> range = Flux.range(1, 50);
        StepVerifier.create(range)
                .expectNextCount(50)
                .verifyComplete();

        StepVerifier.create(range)
                .thenConsumeWhile(i -> i < 100)
                .verifyComplete();

    }

    @Test
    void withDelay() {
        Mono<BookOrder> mono = Mono.fromSupplier(() -> new BookOrder())
                .delayElement(Duration.ofSeconds(2));

        StepVerifier.create(mono)
                .assertNext(bookOrder -> assertNotNull(bookOrder.getAuthor()))
                .expectComplete()
                .verify(Duration.ofSeconds(3));

    }

    @Test
    void withVirtualTime() {
        // simulates specified duration
        // the supplied flux MUST be initialized inside the lambda expression.
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a", "5a")
                .verifyComplete();

        // verifies no activity for the first 4 seconds
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a", "5a")
                .verifyComplete();
    }

    @Test
    void withTestScenarioName() {
        Flux<String> just = Flux.just("a", "b", "c");
        // output when test fails: [test-with-scenario-name] expectation "expectNext(b)" failed (expected value: b; actual value: d)
        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("test-with-scenario-name");
        StepVerifier.create(just, scenarioName)
                .expectNext("a")
                .expectNext("b")
                .expectNext("c")
                .verifyComplete();

        Flux<String> otherJust = Flux.just("a", "b", "c");

        // output when test fails: expectation "step-b" failed (expected value: b; actual value: d)
        StepVerifier.create(otherJust)
                .expectNext("a")
                .as("step-a")
                .expectNext("b")
                .as("step-b")
                .expectNext("c")
                .as("step-c")
                .verifyComplete();
    }

    @Test
    void withErrorOnMissingContext() {
        StepVerifier.create(new ContextDemo().getWelcomeMessage())
                .verifyError(RuntimeException.class);
    }
    
    @Test
    void withContext() {
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "lionel"));
        StepVerifier.create(new ContextDemo().getWelcomeMessage(), options)
                .expectNext("Welcome lionel")
                .verifyComplete();
    }

    Flux<String> timeConsumingFlux() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + "a");
    }

}
