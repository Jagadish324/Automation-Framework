package com.arc.frameworkWeb.exception;

/**
 * Exception thrown when an element is not stable after multiple retry attempts.
 * This exception indicates that the element did not reach a stable state (visible, displayed)
 * within the configured timeout and retry attempts.
 */
public class ElementNotStableException extends RuntimeException {

    /**
     * Constructs a new ElementNotStableException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception
     */
    public ElementNotStableException(String message) {
        super(message);
    }

    /**
     * Constructs a new ElementNotStableException with the specified detail message and cause.
     *
     * @param message The detail message explaining the reason for the exception
     * @param cause The cause of the exception (which is saved for later retrieval)
     */
    public ElementNotStableException(String message, Throwable cause) {
        super(message, cause);
    }
}
