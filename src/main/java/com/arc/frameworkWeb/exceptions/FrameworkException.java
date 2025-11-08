package com.arc.frameworkWeb.exceptions;

/**
 * Base exception for all framework-related errors.
 * Provides better error categorization and handling.
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.3
 */
public class FrameworkException extends RuntimeException {

    private final ErrorCategory category;
    private final String locator;
    private final String action;

    public FrameworkException(String message) {
        super(message);
        this.category = ErrorCategory.GENERAL;
        this.locator = null;
        this.action = null;
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
        this.category = ErrorCategory.GENERAL;
        this.locator = null;
        this.action = null;
    }

    public FrameworkException(ErrorCategory category, String action, String locator, String message) {
        super(buildMessage(category, action, locator, message));
        this.category = category;
        this.locator = locator;
        this.action = action;
    }

    public FrameworkException(ErrorCategory category, String action, String locator, String message, Throwable cause) {
        super(buildMessage(category, action, locator, message), cause);
        this.category = category;
        this.locator = locator;
        this.action = action;
    }

    private static String buildMessage(ErrorCategory category, String action, String locator, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(category).append("] ");
        if (action != null) {
            sb.append("Action: '").append(action).append("' ");
        }
        if (locator != null) {
            sb.append("Locator: '").append(locator).append("' ");
        }
        sb.append("- ").append(message);
        return sb.toString();
    }

    public ErrorCategory getCategory() {
        return category;
    }

    public String getLocator() {
        return locator;
    }

    public String getAction() {
        return action;
    }

    /**
     * Error categories for better classification
     */
    public enum ErrorCategory {
        ELEMENT_NOT_FOUND("Element not found or not visible"),
        ELEMENT_NOT_INTERACTABLE("Element not interactable"),
        INVALID_LOCATOR("Invalid or malformed locator"),
        TIMEOUT("Operation timed out"),
        STALE_ELEMENT("Element is stale or no longer in DOM"),
        CONFIGURATION("Framework configuration error"),
        GENERAL("General error");

        private final String description;

        ErrorCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
