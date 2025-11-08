package com.arc.frameworkWeb.exceptions;

/**
 * Exception thrown when an element cannot be found within the specified timeout.
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.3
 */
public class ElementNotFoundException extends FrameworkException {

    public ElementNotFoundException(String locator, int timeoutSeconds) {
        super(
            ErrorCategory.ELEMENT_NOT_FOUND,
            "find element",
            locator,
            String.format("Element not found within %d seconds", timeoutSeconds)
        );
    }

    public ElementNotFoundException(String locator, String message) {
        super(ErrorCategory.ELEMENT_NOT_FOUND, "find element", locator, message);
    }

    public ElementNotFoundException(String locator, String message, Throwable cause) {
        super(ErrorCategory.ELEMENT_NOT_FOUND, "find element", locator, message, cause);
    }
}
