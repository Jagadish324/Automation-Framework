package com.arc.frameworkWeb.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Utility class for implementing retry logic with customizable exception handling.
 * Provides methods to retry operations that may fail due to transient issues.
 */
public class RetryHelper {
    private static final Logger log = LogManager.getLogger(RetryHelper.class.getName());

    /**
     * Retries an operation that returns a value with custom exception handling.
     *
     * @param action The operation to retry (Supplier that returns a value)
     * @param maxRetries The maximum number of retry attempts
     * @param retryableExceptions The exceptions that should trigger a retry
     * @param <T> The return type of the operation
     * @return The result of the operation if successful
     * @throws RuntimeException If all retry attempts fail or a non-retryable exception occurs
     *
     * @example
     * <pre>
     * WebElement element = RetryHelper.retryOnException(
     *     () -> driver.findElement(By.id("dynamicElement")),
     *     3,
     *     NoSuchElementException.class,
     *     StaleElementReferenceException.class
     * );
     * </pre>
     */
    @SafeVarargs
    public static <T> T retryOnException(Supplier<T> action, int maxRetries,
                                          Class<? extends Exception>... retryableExceptions) {
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                T result = action.get();
                if (attempt > 1) {
                    log.info("Operation succeeded on attempt {}/{}", attempt, maxRetries);
                }
                return result;
            } catch (Exception e) {
                lastException = e;
                boolean shouldRetry = retryableExceptions.length == 0 ||
                    Arrays.stream(retryableExceptions).anyMatch(ex -> ex.isInstance(e));

                if (!shouldRetry) {
                    log.error("Non-retryable exception occurred: {}", e.getClass().getSimpleName());
                    throw new RuntimeException("Operation failed with non-retryable exception", e);
                }

                if (attempt < maxRetries) {
                    log.warn("Attempt {}/{} failed with {}: {}. Retrying...",
                            attempt, maxRetries, e.getClass().getSimpleName(), e.getMessage());
                } else {
                    log.error("All {} attempts failed. Last exception: {}",
                            maxRetries, e.getClass().getSimpleName());
                }
            }
        }

        throw new RuntimeException("Operation failed after " + maxRetries + " attempts", lastException);
    }

    /**
     * Retries an operation that doesn't return a value (void operation).
     *
     * @param action The operation to retry (Runnable)
     * @param maxRetries The maximum number of retry attempts
     * @param retryableExceptions The exceptions that should trigger a retry
     * @throws RuntimeException If all retry attempts fail or a non-retryable exception occurs
     *
     * @example
     * <pre>
     * RetryHelper.retryOnExceptionVoid(
     *     () -> driver.findElement(By.id("button")).click(),
     *     3,
     *     ElementNotInteractableException.class,
     *     StaleElementReferenceException.class
     * );
     * </pre>
     */
    @SafeVarargs
    public static void retryOnExceptionVoid(Runnable action, int maxRetries,
                                             Class<? extends Exception>... retryableExceptions) {
        retryOnException(() -> {
            action.run();
            return null;
        }, maxRetries, retryableExceptions);
    }

    /**
     * Retries an operation with exponential backoff delay between attempts.
     *
     * @param action The operation to retry (Supplier that returns a value)
     * @param maxRetries The maximum number of retry attempts
     * @param initialDelayMs Initial delay in milliseconds before first retry
     * @param retryableExceptions The exceptions that should trigger a retry
     * @param <T> The return type of the operation
     * @return The result of the operation if successful
     * @throws RuntimeException If all retry attempts fail or a non-retryable exception occurs
     *
     * @example
     * <pre>
     * WebElement element = RetryHelper.retryWithBackoff(
     *     () -> driver.findElement(By.id("slowElement")),
     *     3,
     *     1000,  // Start with 1 second delay
     *     NoSuchElementException.class
     * );
     * // Delays: 1s, 2s, 4s between retries
     * </pre>
     */
    @SafeVarargs
    public static <T> T retryWithBackoff(Supplier<T> action, int maxRetries,
                                          long initialDelayMs,
                                          Class<? extends Exception>... retryableExceptions) {
        Exception lastException = null;
        long delay = initialDelayMs;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                T result = action.get();
                if (attempt > 1) {
                    log.info("Operation succeeded on attempt {}/{} with backoff", attempt, maxRetries);
                }
                return result;
            } catch (Exception e) {
                lastException = e;
                boolean shouldRetry = retryableExceptions.length == 0 ||
                    Arrays.stream(retryableExceptions).anyMatch(ex -> ex.isInstance(e));

                if (!shouldRetry) {
                    log.error("Non-retryable exception occurred: {}", e.getClass().getSimpleName());
                    throw new RuntimeException("Operation failed with non-retryable exception", e);
                }

                if (attempt < maxRetries) {
                    log.warn("Attempt {}/{} failed with {}. Waiting {}ms before retry...",
                            attempt, maxRetries, e.getClass().getSimpleName(), delay);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                    delay *= 2; // Exponential backoff
                } else {
                    log.error("All {} attempts failed after exponential backoff", maxRetries);
                }
            }
        }

        throw new RuntimeException("Operation failed after " + maxRetries + " attempts with backoff", lastException);
    }

    /**
     * Retries an operation until a condition is met or max retries is reached.
     *
     * @param action The operation to retry (Supplier that returns a value)
     * @param condition A condition to check on the result (returns true if successful)
     * @param maxRetries The maximum number of retry attempts
     * @param delayMs Delay in milliseconds between retries
     * @param <T> The return type of the operation
     * @return The result of the operation if condition is met
     * @throws RuntimeException If condition is not met after all retries
     *
     * @example
     * <pre>
     * String status = RetryHelper.retryUntilCondition(
     *     () -> driver.findElement(By.id("status")).getText(),
     *     text -> text.equals("Success"),
     *     5,
     *     1000  // Wait 1 second between checks
     * );
     * </pre>
     */
    public static <T> T retryUntilCondition(Supplier<T> action,
                                             java.util.function.Predicate<T> condition,
                                             int maxRetries,
                                             long delayMs) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                T result = action.get();
                if (condition.test(result)) {
                    log.info("Condition met on attempt {}/{}", attempt, maxRetries);
                    return result;
                }

                if (attempt < maxRetries) {
                    log.debug("Condition not met on attempt {}/{}. Waiting {}ms...",
                            attempt, maxRetries, delayMs);
                    Thread.sleep(delayMs);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Retry interrupted", e);
            } catch (Exception e) {
                log.warn("Exception on attempt {}/{}: {}", attempt, maxRetries, e.getMessage());
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                }
            }
        }

        throw new RuntimeException("Condition not met after " + maxRetries + " attempts");
    }
}
