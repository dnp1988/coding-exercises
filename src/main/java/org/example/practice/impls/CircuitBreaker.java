package org.example.practice.impls;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Supplier;

public class CircuitBreaker<T> {

    private Supplier<T> supplier;
    private Long retrySeconds;
    private Long attemptLimit;
    private Long failedAttempts = 0L;
    private Throwable lastThrowable;
    private LocalDateTime lastErrorDateTime;

    public CircuitBreaker(Supplier<T> supplier, Long retrySeconds, Long attemptLimit) {
        this.supplier = supplier;
        this.retrySeconds = retrySeconds;
        this.attemptLimit = attemptLimit;
    }

    public T call() throws Throwable {
        if(failedAttempts < attemptLimit) {
            return makeCall();
        }
        if(lastErrorDateTime == null
                || LocalDateTime.now(ZoneId.of("UTC")).isAfter(lastErrorDateTime.plusSeconds(retrySeconds))) {
            return makeCall();
        }
        throw lastThrowable;
    }

    private void clearLastError() {
        this.lastErrorDateTime = null;
        this.lastThrowable = null;
        this.failedAttempts = 0L;
    }

    private T makeCall() {
        try {
            T result = supplier.get();
            clearLastError();
            return result;
        } catch (Throwable throwable) {
            recordFailedAttempt(throwable);
            throw throwable;
        }
    }

    private void recordFailedAttempt(Throwable throwable) {
        this.failedAttempts++;
        this.lastThrowable = throwable;
        this.lastErrorDateTime = LocalDateTime.now(ZoneId.of("UTC"));
    }
}
