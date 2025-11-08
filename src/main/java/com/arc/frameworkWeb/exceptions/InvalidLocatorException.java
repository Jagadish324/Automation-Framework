package com.arc.frameworkWeb.exceptions;

/**
 * Exception thrown when a locator is invalid or malformed.
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.3
 */
public class InvalidLocatorException extends FrameworkException {

    public InvalidLocatorException(String locator, String reason) {
        super(
            ErrorCategory.INVALID_LOCATOR,
            null,
            locator,
            String.format("Invalid locator: %s", reason)
        );
    }

    public InvalidLocatorException(String locator, Throwable cause) {
        super(ErrorCategory.INVALID_LOCATOR, null, locator, "Locator is invalid", cause);
    }
}
