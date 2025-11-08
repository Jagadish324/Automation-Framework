package com.arc.frameworkWeb.exceptions;

/**
 * Exception thrown when an element exists but is not interactable.
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.3
 */
public class ElementNotInteractableException extends FrameworkException {

    public ElementNotInteractableException(String action, String locator, String reason) {
        super(
            ErrorCategory.ELEMENT_NOT_INTERACTABLE,
            action,
            locator,
            String.format("Element not interactable: %s", reason)
        );
    }

    public ElementNotInteractableException(String action, String locator, Throwable cause) {
        super(ErrorCategory.ELEMENT_NOT_INTERACTABLE, action, locator,
            "Element found but not interactable", cause);
    }
}
