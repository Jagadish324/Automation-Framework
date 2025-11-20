package com.arc.frameworkWeb.exception;

/**
 * Exception thrown when an element cannot be found in the DOM after multiple retry attempts.
 * This exception indicates that the element locator did not match any element
 * within the configured timeout and retry attempts.
 */
public class ElementNotFoundException extends RuntimeException {

    /**
     * Constructs a new ElementNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception
     */
    public ElementNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ElementNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message explaining the reason for the exception
     * @param cause The cause of the exception (which is saved for later retrieval)
     */
    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
