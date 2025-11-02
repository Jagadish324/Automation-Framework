package com.arc.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Log {
    // A thread-safe map to cache Logger instances, keyed by class name
    private static final Map<String, Logger> loggers = new ConcurrentHashMap<>();

    // Private constructor to prevent instantiation of this utility class
    private Log() {
    }

    /**
     * Retrieves or creates a cached Logger instance for the given class name.
     * Uses computeIfAbsent to ensure only one Logger is created per class.
     */
    private static Logger getLogger(String className) {
        // Get the logger from the cache, or create it using LogManager if absent
        return loggers.computeIfAbsent(className, LogManager::getLogger);
    }

    /**
     * Formats the log message to include the calling method's name.
     * Example output: "[methodName] Your message here"
     */
    private static String formatMessage(StackTraceElement caller, String message) {
        // Extract the method name from the caller and format the message
        return String.format("[%s] %s", caller.getMethodName(), message);
    }

    /**
     * Logs an informational message with the calling method's context.
     * Supports parameterized messages using '{}' placeholders.
     */
    public static void info(String message, Object... params) {
        // Get the stack trace element representing the method that called 'info'
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Retrieve the Logger for the caller's class
        Logger log = getLogger(caller.getClassName());
        // Format and log the message at INFO level
        log.info(formatMessage(caller, message), params);
    }

    /**
     * Logs a debug-level message with the calling method's context.
     * Useful for debugging during development.
     */
    public static void debug(String message, Object... params) {
        // Get the stack trace element of the calling method
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Get or create the Logger for the calling class
        Logger log = getLogger(caller.getClassName());
        // Format and log the message at DEBUG level
        log.debug(formatMessage(caller, message), params);
    }

    /**
     * Logs a warning message, indicating potential issues.
     */
    public static void warn(String message, Object... params) {
        // Get the stack trace element of the method that called 'warn'
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Retrieve the Logger for the caller's class
        Logger log = getLogger(caller.getClassName());
        // Format and log the message at WARN level
        log.warn(formatMessage(caller, message), params);
    }

    /**
     * Logs an error message, indicating a failure or exception.
     */
    public static void err(String message, Object... params) {
        // Identify the method that triggered the error log
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Get or create a Logger for the caller's class
        Logger log = getLogger(caller.getClassName());
        // Format and log the error message
        log.error(formatMessage(caller, message), params);
    }

    /**
     * Logs a fatal-level message, representing critical failures.
     */
    public static void fatal(String message, Object... params) {
        // Get the method context from the stack trace
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Obtain the appropriate Logger for the calling class
        Logger log = getLogger(caller.getClassName());
        // Log the formatted fatal message
        log.fatal(formatMessage(caller, message), params);
    }

    /**
     * Logs a trace-level message, useful for extremely detailed logging.
     */
    public static void trace(String message, Object... params) {
        // Get the calling method's stack trace element
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2];
        // Fetch the Logger for the caller's class
        Logger log = getLogger(caller.getClassName());
        // Format and log the trace message
        log.trace(formatMessage(caller, message), params);
    }

    /**
     * Logs the current stack trace along with an optional message.
     * Helps in debugging and tracking execution flow.
     */
    public static void logStackTrace(Object message) {
        // Get the full stack trace of the current thread
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // Create a StringBuilder to build the full trace message
        StringBuilder traceBuilder = new StringBuilder("\n=========== Stack Trace ===========\n");

        // Start from index 2 to skip getStackTrace() and logStackTrace() entries
        for (int i = 2; i < stackTrace.length; i++) {
            // Extract current stack trace element
            StackTraceElement element = stackTrace[i];
            // Append formatted stack trace line to the builder
            traceBuilder.append(String.format("Class: %s | Method: %s | Line: %d%n",
                    element.getClassName(), element.getMethodName(), element.getLineNumber()));
        }

        // If a custom message is provided, append it to the trace
        if (message != null) {
            traceBuilder.append("Message: ").append(message).append("\n");
        }

        // Add closing separator for readability
        traceBuilder.append("===================================\n");

        // Get Logger for the method that invoked logStackTrace
        Logger logger = getLogger(stackTrace[2].getClassName());
        // Log the complete stack trace as an info-level log
        logger.info(traceBuilder.toString());
    }
}
