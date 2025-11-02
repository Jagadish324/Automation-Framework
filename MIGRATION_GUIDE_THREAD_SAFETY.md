# Migration Guide: Thread-Safe Parallel Execution

## Overview

This guide explains how to migrate from the static pattern to the new ThreadLocal-based pattern for parallel test execution. The framework now supports both patterns:

- **Legacy (Static)**: Single-threaded execution using static fields *(backward compatible)*
- **New (ThreadLocal)**: Parallel execution using ThreadLocal storage *(recommended)*

## Why Migrate?

### Problems with Static Pattern

```java
// BEFORE: Static pattern (NOT thread-safe)
public class LoginTest {
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        CommonHelper.webDriver = driver;  // ❌ Shared across all threads
        Button.click(By.id("login"));
    }
}
```

**Issues:**
- Cannot run tests in parallel (threads interfere with each other)
- Test isolation problems (one test affects another)
- Difficult to mock for unit testing
- Race conditions when running concurrent tests

### Benefits of ThreadLocal Pattern

```java
// AFTER: ThreadLocal pattern (Thread-safe)
public class LoginTest {
    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        DriverManager.setWebDriver(driver);  // ✅ Thread-specific instance
        Button.click(By.id("login"));
        DriverManager.quitWebDriver();
    }
}
```

**Benefits:**
- ✅ Parallel test execution (each thread has its own driver)
- ✅ Complete test isolation
- ✅ Easier to mock and test
- ✅ No race conditions
- ✅ Automatic cleanup per thread
- ✅ Better resource management

## Migration Steps

### Step 1: Update Driver Initialization

#### Before (Static)

```java
@BeforeEach
public void setup() {
    WebDriver driver = new ChromeDriver();
    CommonHelper.webDriver = driver;  // Static assignment
}

@AfterEach
public void teardown() {
    if (CommonHelper.webDriver != null) {
        CommonHelper.webDriver.quit();
    }
}
```

#### After (ThreadLocal)

```java
@BeforeEach
public void setup() {
    WebDriver driver = new ChromeDriver();
    DriverManager.setWebDriver(driver);  // ThreadLocal assignment
    TestContext.initialize();  // Initialize thread-specific config
}

@AfterEach
public void teardown() {
    DriverManager.quitWebDriver();  // Auto-cleanup with quit
    TestContext.clear();  // Clear thread-specific data
}
```

### Step 2: Update Configuration Management

#### Before (Static)

```java
CONSTANT.BROWSER_TYPE = "chrome";
CONSTANT.IMPLICIT_WAIT = 10;
CONSTANT.TOOL = "selenium";
```

#### After (ThreadLocal)

```java
TestContext.setBrowserType("chrome");
TestContext.setImplicitWait(10);
TestContext.setTool("selenium");
```

### Step 3: Update Test Classes

#### Before (Static)

```java
public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        CommonHelper.webDriver = driver;
        CONSTANT.IMPLICIT_WAIT = 10;
    }

    @Test
    public void testValidLogin() {
        Navigate.get("https://example.com/login");
        TextBox.sendKeys(By.id("username"), "user1");
        TextBox.sendKeys(By.id("password"), "pass123");
        Button.click(By.id("loginBtn"));
        assert Validation.isDisplayed(By.id("dashboard"));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

#### After (ThreadLocal)

```java
public class LoginTest {

    @BeforeEach
    public void setup() {
        WebDriver driver = new ChromeDriver();
        DriverManager.setWebDriver(driver);
        TestContext.initialize();
        TestContext.setImplicitWait(10);
    }

    @Test
    public void testValidLogin() {
        Navigate.get("https://example.com/login");
        TextBox.sendKeys(By.id("username"), "user1");
        TextBox.sendKeys(By.id("password"), "pass123");
        Button.click(By.id("loginBtn"));
        assert Validation.isDisplayed(By.id("dashboard"));
    }

    @AfterEach
    public void teardown() {
        DriverManager.cleanup();  // Quits driver and cleans up
        TestContext.clear();
    }
}
```

## Complete Examples

### Example 1: Base Test Class Pattern

Create a base class that all tests extend:

```java
import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.TestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {

    @BeforeEach
    public void setup() {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        DriverManager.setWebDriver(driver);

        // Initialize test context with default config
        TestContext.initialize();
        TestContext.setTool("selenium");
        TestContext.setBrowserType("chrome");
        TestContext.setImplicitWait(10);
        TestContext.setExplicitWait(10);
        TestContext.setStepRetry(3);
        TestContext.setAutoWaitTime(3);

        // Any additional setup
        maximizeWindow();
    }

    @AfterEach
    public void teardown() {
        // Cleanup - automatically quits driver for this thread
        DriverManager.cleanup();
        TestContext.clear();
    }

    private void maximizeWindow() {
        DriverManager.getWebDriver().manage().window().maximize();
    }
}
```

### Example 2: Individual Test Class

```java
import com.arc.frameworkWeb.helper.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        Navigate.get("https://example.com/login");
        TextBox.sendKeys(By.id("username"), "validuser");
        TextBox.sendKeys(By.id("password"), "validpass");
        Button.click(By.id("loginButton"));

        assert Validation.isDisplayed(By.className("dashboard"));
    }

    @Test
    public void testInvalidLogin() {
        Navigate.get("https://example.com/login");
        TextBox.sendKeys(By.id("username"), "invalid");
        TextBox.sendKeys(By.id("password"), "wrong");
        Button.click(By.id("loginButton"));

        assert Validation.isDisplayed(By.className("error-message"));
    }
}
```

### Example 3: Parallel Execution Configuration

#### Maven Surefire Configuration (pom.xml)

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
    <configuration>
        <!-- Enable parallel execution -->
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
        <perCoreThreadCount>true</perCoreThreadCount>

        <properties>
            <configurationParameters>
                cucumber.junit-platform.naming-strategy=long
                <!-- JUnit 5 parallel execution -->
                junit.jupiter.execution.parallel.enabled=true
                junit.jupiter.execution.parallel.mode.default=concurrent
                junit.jupiter.execution.parallel.config.strategy=fixed
                junit.jupiter.execution.parallel.config.fixed.parallelism=4
            </configurationParameters>
        </properties>
    </configuration>
</plugin>
```

#### JUnit 5 Configuration (junit-platform.properties)

Create `src/test/resources/junit-platform.properties`:

```properties
# Enable parallel execution
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.mode.classes.default=concurrent
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=4
```

### Example 4: Cucumber with Parallel Execution

```java
import com.arc.frameworkWeb.context.DriverManager;
import com.arc.frameworkWeb.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        // Each scenario gets its own driver instance
        WebDriver driver = new ChromeDriver();
        DriverManager.setWebDriver(driver);

        TestContext.initialize();
        TestContext.setTool("selenium");
        TestContext.put("scenarioName", scenario.getName());

        DriverManager.getWebDriver().manage().window().maximize();
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Capture screenshot on failure
        if (scenario.isFailed()) {
            // Screenshot logic here
        }

        // Cleanup this thread's resources
        DriverManager.cleanup();
        TestContext.clear();
    }
}
```

### Example 5: Using TestContext for Custom Data

```java
public class ShoppingCartTest extends BaseTest {

    @Test
    public void testAddToCart() {
        // Store test data in thread-specific context
        TestContext.put("productId", "12345");
        TestContext.put("quantity", 2);

        // Navigate and add product
        Navigate.get("https://example.com/product/12345");
        Button.click(By.id("addToCart"));

        // Retrieve from context later
        String productId = TestContext.get("productId", String.class);
        Integer quantity = TestContext.get("quantity", Integer.class);

        // Validate cart
        Navigate.get("https://example.com/cart");
        assert Validation.isDisplayed(By.xpath("//div[@data-product-id='" + productId + "']"));
    }
}
```

### Example 6: Playwright Usage

```java
import com.arc.frameworkWeb.context.DriverManager;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class PlaywrightTest {

    private Playwright playwright;
    private Browser browser;

    @BeforeEach
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        DriverManager.setPage(page);
        TestContext.initialize();
        TestContext.setTool("playwright");
    }

    @Test
    public void testWithPlaywright() {
        Page page = DriverManager.getPage();
        page.navigate("https://example.com");

        // Framework helpers work with Playwright too
        Button.click(By.id("loginBtn"));
    }

    @AfterEach
    public void teardown() {
        DriverManager.closePage();
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        TestContext.clear();
    }
}
```

## API Reference

### DriverManager Class

| Method | Description |
|--------|-------------|
| `setWebDriver(WebDriver)` | Sets WebDriver for current thread |
| `getWebDriver()` | Gets WebDriver for current thread |
| `hasWebDriver()` | Checks if WebDriver exists for this thread |
| `setPage(Page)` | Sets Playwright Page for current thread |
| `getPage()` | Gets Playwright Page for current thread |
| `hasPage()` | Checks if Page exists for this thread |
| `quitWebDriver()` | Quits WebDriver and removes from ThreadLocal |
| `closePage()` | Closes Page and removes from ThreadLocal |
| `removeWebDriver()` | Removes WebDriver without quitting |
| `removePage()` | Removes Page without closing |
| `cleanup()` | Quits WebDriver and closes Page |

### TestContext Class

| Method | Description |
|--------|-------------|
| `initialize()` | Initializes context with default values |
| `setBrowserType(String)` / `getBrowserType()` | Browser type (chrome, firefox, etc.) |
| `setTool(String)` / `getTool()` | Tool type (selenium, playwright) |
| `setImplicitWait(int)` / `getImplicitWait()` | Implicit wait time in seconds |
| `setExplicitWait(int)` / `getExplicitWait()` | Explicit wait time in seconds |
| `setStepRetry(int)` / `getStepRetry()` | Number of retry attempts |
| `setAutoWaitTime(int)` / `getAutoWaitTime()` | Auto-wait time in seconds |
| `put(String, Object)` | Store custom data |
| `get(String)` | Retrieve custom data |
| `get(String, Class<T>)` | Retrieve custom data with type |
| `contains(String)` | Check if key exists |
| `remove(String)` | Remove custom data entry |
| `clear()` | Clear all context data for thread |

## Backward Compatibility

The framework maintains **100% backward compatibility**. Existing tests using the static pattern will continue to work:

```java
// Old code still works
WebDriver driver = new ChromeDriver();
CommonHelper.webDriver = driver;  // Still functional
Button.click(By.id("button"));

// But won't support parallel execution
```

The helpers automatically detect and use:
1. ThreadLocal instance (if set) - **Preferred**
2. Static instance (if ThreadLocal not set) - **Fallback**

## Testing the Migration

### 1. Verify Single Test Works

```bash
mvn test -Dtest=LoginTest#testValidLogin
```

### 2. Verify Parallel Execution

```bash
mvn test -Dparallel=methods -DthreadCount=4
```

### 3. Check for Thread Safety Issues

Look for these warning signs:
- Tests fail randomly when run in parallel
- "No WebDriver found" exceptions
- Tests pass individually but fail in parallel
- Incorrect test data in assertions

## Common Pitfalls

### 1. Forgetting to Call cleanup()

```java
// ❌ BAD: Memory leak
@AfterEach
public void teardown() {
    // Forgot to cleanup!
}

// ✅ GOOD: Proper cleanup
@AfterEach
public void teardown() {
    DriverManager.cleanup();
    TestContext.clear();
}
```

### 2. Sharing Driver Between Threads

```java
// ❌ BAD: Sharing driver
private static WebDriver sharedDriver;  // Don't do this!

@BeforeEach
public void setup() {
    if (sharedDriver == null) {
        sharedDriver = new ChromeDriver();
    }
    DriverManager.setWebDriver(sharedDriver);
}

// ✅ GOOD: Each thread gets its own driver
@BeforeEach
public void setup() {
    WebDriver driver = new ChromeDriver();  // New instance per thread
    DriverManager.setWebDriver(driver);
}
```

### 3. Not Initializing TestContext

```java
// ❌ BAD: Using config without initialization
@Test
public void test() {
    TestContext.setImplicitWait(10);  // NullPointerException!
}

// ✅ GOOD: Initialize first
@BeforeEach
public void setup() {
    TestContext.initialize();  // Initialize with defaults
    TestContext.setImplicitWait(10);
}
```

## Performance Considerations

### Before (Sequential)

```
Test 1: 10s
Test 2: 10s
Test 3: 10s
Total: 30s
```

### After (Parallel with 3 threads)

```
Test 1: 10s ║
Test 2: 10s ║ Run concurrently
Test 3: 10s ║
Total: ~10s (3x faster!)
```

## Gradual Migration Strategy

You can migrate gradually:

1. **Start with new tests**: Write new tests using ThreadLocal pattern
2. **Update base classes**: Migrate base test classes first
3. **Update high-value tests**: Migrate tests that run frequently
4. **Batch migration**: Migrate remaining tests in batches
5. **Deprecation**: Eventually remove static pattern (optional)

## Need Help?

- Check the examples in this guide
- Review Javadoc in `DriverManager` and `TestContext`
- Look at the framework's test classes for working examples
- Contact framework maintainers

---

**Migration Checklist:**

- [ ] Updated driver initialization to use `DriverManager.setWebDriver()`
- [ ] Updated teardown to call `DriverManager.cleanup()`
- [ ] Replaced `CONSTANT.*` with `TestContext.*` methods
- [ ] Added `TestContext.initialize()` in setup
- [ ] Added `TestContext.clear()` in teardown
- [ ] Configured parallel execution in Maven/JUnit
- [ ] Tested single test execution
- [ ] Tested parallel execution
- [ ] Verified no thread safety issues
- [ ] Updated CI/CD pipeline (if applicable)

Happy testing! 🚀
