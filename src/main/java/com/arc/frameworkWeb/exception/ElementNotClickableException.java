package com.arc.frameworkWeb.exception;

/**
 * Exception thrown when an element is not clickable after multiple retry attempts.
 * This exception indicates that the element did not become clickable
 * within the configured timeout and retry attempts.
 */
public class ElementNotClickableException extends RuntimeException {

    /**
     * Constructs a new ElementNotClickableException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception
     */
    public ElementNotClickableException(String message) {
        super(message);
    }

    /**
     * Constructs a new ElementNotClickableException with the specified detail message and cause.
     *
     * @param message The detail message explaining the reason for the exception
     * @param cause The cause of the exception (which is saved for later retrieval)
     */
    public ElementNotClickableException(String message, Throwable cause) {
        super(message, cause);
    }
}
