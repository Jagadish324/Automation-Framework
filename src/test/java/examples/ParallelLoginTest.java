package examples;

import com.arc.frameworkWeb.context.TestContext;
import com.arc.frameworkWeb.helper.Button;
import com.arc.frameworkWeb.helper.Navigate;
import com.arc.frameworkWeb.helper.TextBox;
import com.arc.frameworkWeb.helper.Validation;
import com.arc.helper.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example test class demonstrating thread-safe parallel test execution with JUnit 5.11.3.
 *
 * <p>These tests can run in parallel without interfering with each other
 * because each thread has its own WebDriver instance managed by DriverManager.</p>
 *
 * <p><b>JUnit 5 Features Demonstrated:</b></p>
 * <ul>
 *   <li>@DisplayName - Descriptive test names for reports</li>
 *   <li>@Tag - Categorize tests for selective execution</li>
 *   <li>@Execution - Parallel execution configuration</li>
 *   <li>Improved assertions with custom messages</li>
 *   <li>Thread-safe test execution</li>
 * </ul>
 *
 * <p>To run in parallel, configure Maven Surefire or JUnit properties:</p>
 * <pre>
 * junit.jupiter.execution.parallel.enabled=true
 * junit.jupiter.execution.parallel.mode.default=concurrent
 * </pre>
 *
 * @author Automation Framework Team
 * @version 2.0
 * @since 0.0.5
 */
@DisplayName("Login Feature - Parallel Test Suite")
@Tag("login")
@Tag("regression")
@Execution(ExecutionMode.CONCURRENT)  // Enable parallel execution for this class
public class ParallelLoginTest extends BaseTestThreadSafe {

    private static final String LOGIN_URL = "https://example.com/login";

    @Test
    @DisplayName("Should successfully login with valid credentials for User 1")
    @Tag("smoke")
    void testValidLogin_User1() {
        Log.info("Starting test: testValidLogin_User1 on thread: {}", Thread.currentThread().getName());

        // Store test-specific data in thread-local context
        TestContext.put("testUser", "user1");
        TestContext.put("testName", "testValidLogin_User1");

        // Navigate to login page
        Navigate.get(LOGIN_URL);

        // Enter credentials
        TextBox.sendKeys(By.id("username"), "validuser1");
        TextBox.sendKeys(By.id("password"), "password123");

        // Click login button
        Button.click(By.id("loginButton"));

        // Validate successful login with descriptive assertion message
        assertAll("Login validation for User 1",
            () -> assertTrue(Validation.isDisplayed(By.className("dashboard")),
                "Dashboard should be displayed after valid login"),
            () -> assertEquals("user1", TestContext.get("testUser", String.class),
                "Test user should be stored in context")
        );

        Log.info("Completed test: testValidLogin_User1");
    }

    @Test
    @DisplayName("Should successfully login with valid credentials for User 2")
    @Tag("smoke")
    void testValidLogin_User2() {
        Log.info("Starting test: testValidLogin_User2 on thread: {}", Thread.currentThread().getName());

        TestContext.put("testUser", "user2");
        TestContext.put("testName", "testValidLogin_User2");

        Navigate.get(LOGIN_URL);

        TextBox.sendKeys(By.id("username"), "validuser2");
        TextBox.sendKeys(By.id("password"), "password456");

        Button.click(By.id("loginButton"));

        assertTrue(Validation.isDisplayed(By.className("dashboard")),
            () -> "Dashboard should be displayed after valid login for user: " +
                  TestContext.get("testUser", String.class));

        Log.info("Completed test: testValidLogin_User2");
    }

    @Test
    @DisplayName("Should display error message when login fails with wrong password")
    @Tag("negative")
    void testInvalidLogin_WrongPassword() {
        Log.info("Starting test: testInvalidLogin_WrongPassword on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testInvalidLogin_WrongPassword");

        Navigate.get(LOGIN_URL);

        TextBox.sendKeys(By.id("username"), "validuser");
        TextBox.sendKeys(By.id("password"), "wrongpassword");

        Button.click(By.id("loginButton"));

        // Validate error message is displayed with improved assertion
        assertTrue(Validation.isDisplayed(By.className("error-message")),
            "Error message should be displayed for invalid credentials");

        // Additional validation that dashboard is NOT displayed
        assertFalse(Validation.isDisplayed(By.className("dashboard")),
            "Dashboard should not be displayed with invalid credentials");

        Log.info("Completed test: testInvalidLogin_WrongPassword");
    }

    @Test
    @DisplayName("Should display validation error when submitting empty login form")
    @Tag("negative")
    @Tag("validation")
    void testInvalidLogin_EmptyFields() {
        Log.info("Starting test: testInvalidLogin_EmptyFields on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testInvalidLogin_EmptyFields");

        Navigate.get(LOGIN_URL);

        // Don't enter any credentials
        Button.click(By.id("loginButton"));

        // Validate validation message
        assertTrue(Validation.isDisplayed(By.className("validation-error")),
            "Validation error should be displayed for empty fields");

        Log.info("Completed test: testInvalidLogin_EmptyFields");
    }

    @Test
    @DisplayName("Should navigate to forgot password page when clicking forgot password link")
    @Tag("smoke")
    void testForgotPassword() {
        Log.info("Starting test: testForgotPassword on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testForgotPassword");

        Navigate.get(LOGIN_URL);

        // Click forgot password link
        Button.click(By.linkText("Forgot Password?"));

        // Validate we're on forgot password page
        assertTrue(Validation.isDisplayed(By.id("reset-password-form")),
            "Reset password form should be displayed on forgot password page");

        Log.info("Completed test: testForgotPassword");
    }
}
