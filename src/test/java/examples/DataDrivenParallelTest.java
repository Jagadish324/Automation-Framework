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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example of data-driven parallel testing using JUnit 5.11.3 parameterized tests.
 *
 * <p><b>This demonstrates:</b></p>
 * <ul>
 *   <li>Thread-safe parameterized tests</li>
 *   <li>Each test execution gets its own WebDriver instance</li>
 *   <li>Tests can run in parallel without interference</li>
 *   <li>Test data is managed per thread using TestContext</li>
 *   <li>@ParameterizedTest with @CsvSource for data-driven testing</li>
 *   <li>@DisplayName with dynamic parameter placeholders</li>
 *   <li>@Tag for test categorization</li>
 * </ul>
 *
 * <p><b>JUnit 5 Parameterized Test Features:</b></p>
 * <ul>
 *   <li>{index} - Display the invocation index</li>
 *   <li>{arguments} - Display all arguments</li>
 *   <li>{0}, {1}, etc. - Display specific arguments by position</li>
 * </ul>
 *
 * @author Automation Framework Team
 * @version 2.0
 * @since 0.0.5
 */
@DisplayName("Data-Driven Parallel Test Suite")
@Tag("data-driven")
@Tag("regression")
@Execution(ExecutionMode.CONCURRENT)
public class DataDrivenParallelTest extends BaseTestThreadSafe {

    @ParameterizedTest(name = "[{index}] Login test: username=''{0}'', password=''{1}'', expected=''{2}''")
    @DisplayName("Should handle various login credential combinations")
    @Tag("login")
    @Tag("smoke")
    @CsvSource({
        "validuser, validpass, success",
        "invaliduser, anypass, error",
        "validuser, wrongpass, error",
        "emptyuser, validpass, error",
        "validuser, emptypass, error"
    })
    void testLoginWithVariousCredentials(String username, String password, String expectedResult) {
        Log.info("Testing login with username: {} on thread: {}", username, Thread.currentThread().getName());

        // Store test data in thread-specific context
        TestContext.put("username", username);
        TestContext.put("password", password);
        TestContext.put("expectedResult", expectedResult);

        // Navigate to login page
        Navigate.get("https://example.com/login");

        // Enter credentials (handle empty values)
        if (!username.equals("emptyuser")) {
            TextBox.sendKeys(By.id("username"), username);
        }
        if (!password.equals("emptypass")) {
            TextBox.sendKeys(By.id("password"), password);
        }

        // Click login
        Button.click(By.id("loginButton"));

        // Validate result based on expected outcome with improved assertions
        if (expectedResult.equals("success")) {
            assertAll("Successful login validation for " + username,
                () -> assertTrue(Validation.isDisplayed(By.className("dashboard")),
                    "Dashboard should be displayed for valid credentials: " + username),
                () -> assertFalse(Validation.isDisplayed(By.className("error-message")),
                    "No error message should be shown for valid credentials")
            );
        } else {
            assertAll("Failed login validation for " + username,
                () -> assertTrue(Validation.isDisplayed(By.className("error-message")),
                    "Error message should be displayed for invalid credentials: " + username),
                () -> assertFalse(Validation.isDisplayed(By.className("dashboard")),
                    "Dashboard should not be displayed for invalid credentials")
            );
        }

        Log.info("Completed test for username: {} with expected result: {}", username, expectedResult);
    }

    @ParameterizedTest(name = "[{index}] Search test: query=''{0}'', minimum results={1}")
    @DisplayName("Should return search results for various product queries")
    @Tag("search")
    @Tag("smoke")
    @CsvSource({
        "laptop, 10",
        "phone, 15",
        "tablet, 8",
        "headphones, 12",
        "keyboard, 20"
    })
    void testSearchWithDifferentQueries(String searchQuery, int expectedMinResults) {
        Log.info("Testing search with query: '{}', expecting at least {} results on thread: {}",
            searchQuery, expectedMinResults, Thread.currentThread().getName());

        TestContext.put("searchQuery", searchQuery);
        TestContext.put("expectedMinResults", expectedMinResults);

        // Navigate to home page
        Navigate.get("https://example.com");

        // Perform search
        TextBox.sendKeys(By.id("searchBox"), searchQuery);
        Button.click(By.id("searchButton"));

        // Validate results with improved assertions
        assertAll("Search results validation for query: " + searchQuery,
            () -> assertTrue(Validation.isDisplayed(By.className("search-results")),
                "Search results should be displayed for query: " + searchQuery),
            () -> assertNotNull(searchQuery,
                "Search query should not be null"),
            () -> assertTrue(expectedMinResults > 0,
                () -> "Expected minimum results should be positive, got: " + expectedMinResults)
        );

        Log.info("Completed search test for: '{}', expected minimum {} results", searchQuery, expectedMinResults);
    }
}
