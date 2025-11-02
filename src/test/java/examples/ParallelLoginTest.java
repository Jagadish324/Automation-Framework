package examples;

import com.arc.frameworkWeb.context.TestContext;
import com.arc.frameworkWeb.helper.Button;
import com.arc.frameworkWeb.helper.Navigate;
import com.arc.frameworkWeb.helper.TextBox;
import com.arc.frameworkWeb.helper.Validation;
import com.arc.helper.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example test class demonstrating thread-safe parallel test execution.
 *
 * These tests can run in parallel without interfering with each other
 * because each thread has its own WebDriver instance managed by DriverManager.
 *
 * To run in parallel, configure Maven Surefire or JUnit properties:
 * - junit.jupiter.execution.parallel.enabled=true
 * - junit.jupiter.execution.parallel.mode.default=concurrent
 *
 * @author Automation Framework Team
 * @version 1.0
 */
@Execution(ExecutionMode.CONCURRENT)  // Enable parallel execution for this class
public class ParallelLoginTest extends BaseTestThreadSafe {

    private static final String LOGIN_URL = "https://example.com/login";

    @Test
    public void testValidLogin_User1() {
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

        // Validate successful login
        boolean isDashboardDisplayed = Validation.isDisplayed(By.className("dashboard"));
        assertTrue(isDashboardDisplayed, "Dashboard should be displayed after valid login");

        Log.info("Completed test: testValidLogin_User1");
    }

    @Test
    public void testValidLogin_User2() {
        Log.info("Starting test: testValidLogin_User2 on thread: {}", Thread.currentThread().getName());

        TestContext.put("testUser", "user2");
        TestContext.put("testName", "testValidLogin_User2");

        Navigate.get(LOGIN_URL);

        TextBox.sendKeys(By.id("username"), "validuser2");
        TextBox.sendKeys(By.id("password"), "password456");

        Button.click(By.id("loginButton"));

        boolean isDashboardDisplayed = Validation.isDisplayed(By.className("dashboard"));
        assertTrue(isDashboardDisplayed, "Dashboard should be displayed after valid login");

        Log.info("Completed test: testValidLogin_User2");
    }

    @Test
    public void testInvalidLogin_WrongPassword() {
        Log.info("Starting test: testInvalidLogin_WrongPassword on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testInvalidLogin_WrongPassword");

        Navigate.get(LOGIN_URL);

        TextBox.sendKeys(By.id("username"), "validuser");
        TextBox.sendKeys(By.id("password"), "wrongpassword");

        Button.click(By.id("loginButton"));

        // Validate error message is displayed
        boolean isErrorDisplayed = Validation.isDisplayed(By.className("error-message"));
        assertTrue(isErrorDisplayed, "Error message should be displayed for invalid credentials");

        Log.info("Completed test: testInvalidLogin_WrongPassword");
    }

    @Test
    public void testInvalidLogin_EmptyFields() {
        Log.info("Starting test: testInvalidLogin_EmptyFields on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testInvalidLogin_EmptyFields");

        Navigate.get(LOGIN_URL);

        // Don't enter any credentials
        Button.click(By.id("loginButton"));

        // Validate validation message
        boolean isValidationDisplayed = Validation.isDisplayed(By.className("validation-error"));
        assertTrue(isValidationDisplayed, "Validation error should be displayed for empty fields");

        Log.info("Completed test: testInvalidLogin_EmptyFields");
    }

    @Test
    public void testForgotPassword() {
        Log.info("Starting test: testForgotPassword on thread: {}", Thread.currentThread().getName());

        TestContext.put("testName", "testForgotPassword");

        Navigate.get(LOGIN_URL);

        // Click forgot password link
        Button.click(By.linkText("Forgot Password?"));

        // Validate we're on forgot password page
        boolean isForgotPasswordPage = Validation.isDisplayed(By.id("reset-password-form"));
        assertTrue(isForgotPasswordPage, "Should navigate to forgot password page");

        Log.info("Completed test: testForgotPassword");
    }
}
