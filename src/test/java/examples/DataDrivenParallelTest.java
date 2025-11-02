package examples;

import com.arc.frameworkWeb.context.TestContext;
import com.arc.frameworkWeb.helper.Button;
import com.arc.frameworkWeb.helper.Navigate;
import com.arc.frameworkWeb.helper.TextBox;
import com.arc.frameworkWeb.helper.Validation;
import com.arc.helper.Log;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example of data-driven parallel testing using JUnit 5 parameterized tests.
 *
 * This demonstrates:
 * - Thread-safe parameterized tests
 * - Each test execution gets its own WebDriver instance
 * - Tests can run in parallel without interference
 * - Test data is managed per thread
 *
 * @author Automation Framework Team
 * @version 1.0
 */
@Execution(ExecutionMode.CONCURRENT)
public class DataDrivenParallelTest extends BaseTestThreadSafe {

    @ParameterizedTest(name = "Test login with username={0}, expected={2}")
    @CsvSource({
        "validuser, validpass, success",
        "invaliduser, anypass, error",
        "validuser, wrongpass, error",
        "emptyuser, validpass, error",
        "validuser, emptypass, error"
    })
    public void testLoginWithVariousCredentials(String username, String password, String expectedResult) {
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

        // Validate result based on expected outcome
        if (expectedResult.equals("success")) {
            boolean isDashboardDisplayed = Validation.isDisplayed(By.className("dashboard"));
            assertEquals(true, isDashboardDisplayed, "Expected successful login for " + username);
        } else {
            boolean isErrorDisplayed = Validation.isDisplayed(By.className("error-message"));
            assertEquals(true, isErrorDisplayed, "Expected error message for " + username);
        }

        Log.info("Completed test for username: {}", username);
    }

    @ParameterizedTest(name = "Test search with query={0}")
    @CsvSource({
        "laptop, 10",
        "phone, 15",
        "tablet, 8",
        "headphones, 12",
        "keyboard, 20"
    })
    public void testSearchWithDifferentQueries(String searchQuery, int expectedMinResults) {
        Log.info("Testing search with query: {} on thread: {}", searchQuery, Thread.currentThread().getName());

        TestContext.put("searchQuery", searchQuery);

        // Navigate to home page
        Navigate.get("https://example.com");

        // Perform search
        TextBox.sendKeys(By.id("searchBox"), searchQuery);
        Button.click(By.id("searchButton"));

        // Validate results
        boolean resultsDisplayed = Validation.isDisplayed(By.className("search-results"));
        assertEquals(true, resultsDisplayed, "Search results should be displayed for: " + searchQuery);

        Log.info("Completed search test for: {}", searchQuery);
    }
}
