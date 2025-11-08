package com.arc.frameworkWeb.utility;

import org.openqa.selenium.devtools.DevTools;

/**
 * Framework configuration constants.
 * These values should be set during framework initialization from configuration files.
 *
 * <p><b>Recommended Wait Strategy Defaults:</b></p>
 * <ul>
 *   <li>IMPLICIT_WAIT = 0 (avoid mixing implicit and explicit waits)</li>
 *   <li>EXPLICIT_WAIT = 10 seconds (reasonable default for most scenarios)</li>
 *   <li>STEP_RETRY = 3 (retry operations 3 times before failing)</li>
 *   <li>AUTOWAIT_TIME = 3 seconds (wait 3 seconds per retry attempt)</li>
 * </ul>
 *
 * @author Automation Framework Team
 * @version 0.0.4
 */
public class CONSTANT {

    public static String BROWSER_TYPE;
    public static String TOOL = "selenium";
    public static String URL;
    public static String PLATFORM;

    /**
     * Implicit wait in seconds. Recommended: 0 (do not mix with explicit waits)
     */
    public static int IMPLICIT_WAIT = 0;

    /**
     * Explicit wait timeout in seconds. Recommended: 10
     */
    public static int EXPLICIT_WAIT = 10;

    public static int FLUENT_WAIT;
    public static int POLLING_TIME;
    public static int DEFAULT_WAIT;
    public static String ORIGINAL_WINDOW;

    /**
     * Number of retry attempts for auto-wait operations. Recommended: 3
     */
    public static int STEP_RETRY = 3;

    /**
     * Wait time in seconds for each auto-wait retry attempt. Recommended: 3
     */
    public static int AUTOWAIT_TIME = 3;

    public static String BROAD_CAST_NAME;
    public static DevTools DEVTOOLS;

    /**
     * Delay in milliseconds before performing an action. Recommended: 0 (use explicit waits instead)
     */
    public static int AUTO_DELAY_BEFORE_ACTION = 0;

    /**
     * Delay in milliseconds after performing an action. Recommended: 0 (use explicit waits instead)
     */
    public static int AUTO_DELAY_AFTER_ACTION = 0;
}
