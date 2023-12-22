package utils;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.time.temporal.ChronoUnit.SECONDS;

public class RetryOn<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryOn.class);

    public T result(final Supplier<T> actionToRetry, final Predicate<T> retryCondition, final int maxAttempts, final int waitDuration) {
        RetryConfig config = RetryConfig.<T>custom()
                .maxAttempts(maxAttempts)
                .waitDuration(Duration.of(waitDuration, SECONDS))
                .retryOnResult(retryCondition)
                .failAfterMaxAttempts(true)
                .build();

        Retry retry = Retry.of("Retry on result", config);

        retry.getEventPublisher().onRetry(event -> {
            int numberOfAttempts = event.getNumberOfRetryAttempts();
            long waitInterval = event.getWaitInterval().getSeconds();
            long timeSpend = numberOfAttempts * waitInterval;
            LOGGER.info("Retrying the request ... Retry attempt: {}; Time spend on retrying: {}s.", numberOfAttempts, timeSpend);
        });
        retry.getEventPublisher().onSuccess(event -> LOGGER.info("Retries led to successful result on attempt number {}.", event.getNumberOfRetryAttempts()));

        return retry.executeSupplier(actionToRetry);
    }
}
