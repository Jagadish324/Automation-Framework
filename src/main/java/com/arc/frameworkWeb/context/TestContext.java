package com.arc.frameworkWeb.context;

import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal-based TestContext for managing test-specific configuration and state.
 *
 * This class provides thread-safe storage for test configuration, constants, and
 * runtime state that was previously stored in static fields. This enables:
 * - Parallel test execution with different configurations
 * - Test isolation
 * - Thread-safe configuration management
 *
 * Usage Example:
 * <pre>
 * // Initialize context for test
 * TestContext.initialize();
 *
 * // Set configuration
 * TestContext.setBrowserType("chrome");
 * TestContext.setTool("selenium");
 * TestContext.setImplicitWait(10);
 *
 * // Get configuration
 * String browser = TestContext.getBrowserType();
 * int wait = TestContext.getImplicitWait();
 *
 * // Store custom data
 * TestContext.put("userId", "12345");
 * String userId = TestContext.get("userId");
 *
 * // Clean up after test
 * TestContext.clear();
 * </pre>
 *
 * @author Automation Framework Team
 * @version 1.0
 * @since 0.0.2
 */
public class TestContext {

    /**
     * ThreadLocal storage for test context data.
     * Each thread maintains its own context with configuration and custom data.
     */
    private static final ThreadLocal<Context> contextThreadLocal = ThreadLocal.withInitial(Context::new);

    /**
     * Private constructor to prevent instantiation.
     */
    private TestContext() {
        // Prevent instantiation
    }

    /**
     * Initializes the context for the current thread with default values.
     * Call this at the beginning of each test.
     */
    public static void initialize() {
        Context context = new Context();
        context.browserType = "chrome";
        context.tool = "selenium";
        context.implicitWait = 10;
        context.explicitWait = 10;
        context.fluentWait = 10;
        context.pollingTime = 2;
        context.defaultWait = 10;
        context.stepRetry = 3;
        context.autoWaitTime = 3;
        context.autoDelayBeforeAction = 0;
        context.autoDelayAfterAction = 0;
        contextThreadLocal.set(context);
    }

    // Browser Type
    public static void setBrowserType(String browserType) {
        contextThreadLocal.get().browserType = browserType;
    }

    public static String getBrowserType() {
        return contextThreadLocal.get().browserType;
    }

    // Tool (selenium/playwright)
    public static void setTool(String tool) {
        contextThreadLocal.get().tool = tool;
    }

    public static String getTool() {
        return contextThreadLocal.get().tool;
    }

    // URL
    public static void setUrl(String url) {
        contextThreadLocal.get().url = url;
    }

    public static String getUrl() {
        return contextThreadLocal.get().url;
    }

    // Platform
    public static void setPlatform(String platform) {
        contextThreadLocal.get().platform = platform;
    }

    public static String getPlatform() {
        return contextThreadLocal.get().platform;
    }

    // Implicit Wait
    public static void setImplicitWait(int implicitWait) {
        contextThreadLocal.get().implicitWait = implicitWait;
    }

    public static int getImplicitWait() {
        return contextThreadLocal.get().implicitWait;
    }

    // Explicit Wait
    public static void setExplicitWait(int explicitWait) {
        contextThreadLocal.get().explicitWait = explicitWait;
    }

    public static int getExplicitWait() {
        return contextThreadLocal.get().explicitWait;
    }

    // Fluent Wait
    public static void setFluentWait(int fluentWait) {
        contextThreadLocal.get().fluentWait = fluentWait;
    }

    public static int getFluentWait() {
        return contextThreadLocal.get().fluentWait;
    }

    // Polling Time
    public static void setPollingTime(int pollingTime) {
        contextThreadLocal.get().pollingTime = pollingTime;
    }

    public static int getPollingTime() {
        return contextThreadLocal.get().pollingTime;
    }

    // Default Wait
    public static void setDefaultWait(int defaultWait) {
        contextThreadLocal.get().defaultWait = defaultWait;
    }

    public static int getDefaultWait() {
        return contextThreadLocal.get().defaultWait;
    }

    // Original Window
    public static void setOriginalWindow(String originalWindow) {
        contextThreadLocal.get().originalWindow = originalWindow;
    }

    public static String getOriginalWindow() {
        return contextThreadLocal.get().originalWindow;
    }

    // Step Retry
    public static void setStepRetry(int stepRetry) {
        contextThreadLocal.get().stepRetry = stepRetry;
    }

    public static int getStepRetry() {
        return contextThreadLocal.get().stepRetry;
    }

    // Auto Wait Time
    public static void setAutoWaitTime(int autoWaitTime) {
        contextThreadLocal.get().autoWaitTime = autoWaitTime;
    }

    public static int getAutoWaitTime() {
        return contextThreadLocal.get().autoWaitTime;
    }

    // Broadcast Name
    public static void setBroadcastName(String broadcastName) {
        contextThreadLocal.get().broadcastName = broadcastName;
    }

    public static String getBroadcastName() {
        return contextThreadLocal.get().broadcastName;
    }

    // DevTools
    public static void setDevTools(DevTools devTools) {
        contextThreadLocal.get().devTools = devTools;
    }

    public static DevTools getDevTools() {
        return contextThreadLocal.get().devTools;
    }

    // Auto Delay Before Action
    public static void setAutoDelayBeforeAction(int autoDelayBeforeAction) {
        contextThreadLocal.get().autoDelayBeforeAction = autoDelayBeforeAction;
    }

    public static int getAutoDelayBeforeAction() {
        return contextThreadLocal.get().autoDelayBeforeAction;
    }

    // Auto Delay After Action
    public static void setAutoDelayAfterAction(int autoDelayAfterAction) {
        contextThreadLocal.get().autoDelayAfterAction = autoDelayAfterAction;
    }

    public static int getAutoDelayAfterAction() {
        return contextThreadLocal.get().autoDelayAfterAction;
    }

    // Custom data storage
    /**
     * Stores custom data in the test context.
     *
     * @param key The key to store the data under
     * @param value The value to store
     */
    public static void put(String key, Object value) {
        contextThreadLocal.get().customData.put(key, value);
    }

    /**
     * Retrieves custom data from the test context.
     *
     * @param key The key to retrieve
     * @return The value, or null if not found
     */
    public static Object get(String key) {
        return contextThreadLocal.get().customData.get(key);
    }

    /**
     * Retrieves custom data with type casting.
     *
     * @param key The key to retrieve
     * @param type The class type to cast to
     * @param <T> The type parameter
     * @return The value cast to the specified type
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, Class<T> type) {
        return (T) contextThreadLocal.get().customData.get(key);
    }

    /**
     * Checks if a key exists in custom data.
     *
     * @param key The key to check
     * @return true if the key exists, false otherwise
     */
    public static boolean contains(String key) {
        return contextThreadLocal.get().customData.containsKey(key);
    }

    /**
     * Removes a custom data entry.
     *
     * @param key The key to remove
     * @return The removed value, or null if not found
     */
    public static Object remove(String key) {
        return contextThreadLocal.get().customData.remove(key);
    }

    /**
     * Clears all data from the current thread's context.
     * Call this in test teardown to prevent memory leaks.
     */
    public static void clear() {
        Context context = contextThreadLocal.get();
        if (context != null) {
            context.customData.clear();
        }
        contextThreadLocal.remove();
    }

    /**
     * Inner class to hold context data for each thread.
     */
    private static class Context {
        String browserType;
        String tool;
        String url;
        String platform;
        int implicitWait;
        int explicitWait;
        int fluentWait;
        int pollingTime;
        int defaultWait;
        String originalWindow;
        int stepRetry;
        int autoWaitTime;
        String broadcastName;
        DevTools devTools;
        int autoDelayBeforeAction;
        int autoDelayAfterAction;
        Map<String, Object> customData = new HashMap<>();
    }
}
