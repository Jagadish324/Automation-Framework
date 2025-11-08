# JUnit 5 Migration Guide

## Overview

The Automation Framework has been updated to use **JUnit 5.11.3** (Jupiter), the latest stable version of the JUnit testing framework. This guide explains the changes and how to write tests with JUnit 5.

## What's New

### Updated Dependencies

**JUnit 5 (Jupiter) - Version 5.11.3:**
- `junit-jupiter` - Aggregator for all Jupiter modules
- `junit-jupiter-api` - API for writing tests
- `junit-jupiter-engine` - Engine for running tests
- `junit-jupiter-params` - Support for parameterized tests

**JUnit Platform - Version 1.11.3:**
- `junit-platform-console` - Console launcher
- `junit-platform-suite` - Test suite support
- `junit-platform-launcher` - Programmatic test execution

**Maven Surefire - Version 3.5.2:**
- Latest version with full JUnit 5 support
- Enhanced parallel execution capabilities
- Better test reporting

### Key Benefits

1. **Better Parallel Execution**: Dynamic thread allocation based on CPU cores
2. **Improved Test Organization**: Support for nested tests, tags, and display names
3. **Parameterized Tests**: Enhanced support for data-driven testing
4. **Conditional Execution**: Run tests based on OS, Java version, environment, etc.
5. **Extension Model**: More powerful and flexible than JUnit 4 rules
6. **Better Assertions**: Improved assertion methods with better error messages

## Migration from JUnit 4 to JUnit 5

### Annotation Changes

| JUnit 4 | JUnit 5 | Description |
|---------|---------|-------------|
| `@Before` | `@BeforeEach` | Runs before each test method |
| `@After` | `@AfterEach` | Runs after each test method |
| `@BeforeClass` | `@BeforeAll` | Runs once before all tests (must be static) |
| `@AfterClass` | `@AfterAll` | Runs once after all tests (must be static) |
| `@Ignore` | `@Disabled` | Disable a test |
| `@Category` | `@Tag` | Tag tests for filtering |
| `@RunWith` | `@ExtendWith` | Extend test behavior |
| `@Rule` | `@ExtendWith` | Use extensions instead |

### Import Changes

```java
// JUnit 4
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

// JUnit 5
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
```

### Assertion Changes

```java
// JUnit 4
Assert.assertEquals("expected", actual);
Assert.assertTrue(condition);
Assert.assertNull(object);

// JUnit 5
Assertions.assertEquals("expected", actual);
Assertions.assertTrue(condition);
Assertions.assertNull(object);

// Or use static import
import static org.junit.jupiter.api.Assertions.*;

assertEquals("expected", actual);
assertTrue(condition);
assertNull(object);
```

## Writing JUnit 5 Tests

### Basic Test Structure

```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Login Feature Tests")
public class LoginTest extends BaseTestThreadSafe {

    @BeforeEach
    void setUp() {
        // Setup code runs before each test
        Navigate.get("https://example.com/login");
    }

    @AfterEach
    void tearDown() {
        // Cleanup code runs after each test
    }

    @Test
    @DisplayName("Should login with valid credentials")
    @Tag("smoke")
    void testValidLogin() {
        TextBox.sendKeys(By.id("username"), "validuser");
        TextBox.sendKeys(By.id("password"), "password123");
        Button.click(By.id("loginButton"));

        assertTrue(Validation.isDisplayed(By.className("dashboard")),
            "Dashboard should be displayed after valid login");
    }

    @Test
    @DisplayName("Should show error with invalid credentials")
    @Tag("regression")
    void testInvalidLogin() {
        TextBox.sendKeys(By.id("username"), "invaliduser");
        TextBox.sendKeys(By.id("password"), "wrongpass");
        Button.click(By.id("loginButton"));

        assertTrue(Validation.isDisplayed(By.className("error")),
            "Error message should be displayed");
    }

    @Test
    @Disabled("Feature not implemented yet")
    void testSocialLogin() {
        // This test will be skipped
    }
}
```

### Parameterized Tests

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class SearchTest extends BaseTestThreadSafe {

    @ParameterizedTest(name = "Search for {0} should return {1} results")
    @CsvSource({
        "laptop, 10",
        "phone, 15",
        "tablet, 8"
    })
    void testSearchResults(String query, int expectedMin) {
        TextBox.sendKeys(By.id("search"), query);
        Button.click(By.id("searchButton"));

        int resultCount = getSearchResultCount();
        assertTrue(resultCount >= expectedMin,
            String.format("Expected at least %d results for '%s'", expectedMin, query));
    }

    @ParameterizedTest
    @ValueSource(strings = {"user1", "user2", "user3"})
    void testMultipleUsers(String username) {
        // Test runs once for each username
        loginAs(username);
        assertTrue(isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("provideLoginCredentials")
    void testLoginWithDifferentCredentials(String username, String password, boolean shouldSucceed) {
        performLogin(username, password);
        assertEquals(shouldSucceed, isLoggedIn());
    }

    static Stream<Arguments> provideLoginCredentials() {
        return Stream.of(
            Arguments.of("validuser", "validpass", true),
            Arguments.of("invaliduser", "anypass", false),
            Arguments.of("validuser", "wrongpass", false)
        );
    }
}
```

### Nested Tests

```java
import org.junit.jupiter.api.Nested;

@DisplayName("Shopping Cart Tests")
public class ShoppingCartTest extends BaseTestThreadSafe {

    @Nested
    @DisplayName("When cart is empty")
    class EmptyCart {

        @BeforeEach
        void clearCart() {
            // Ensure cart is empty
        }

        @Test
        @DisplayName("Should show empty cart message")
        void showEmptyMessage() {
            assertTrue(Validation.isDisplayed(By.className("empty-cart")));
        }

        @Test
        @DisplayName("Checkout button should be disabled")
        void checkoutDisabled() {
            assertFalse(ElementInfo.isEnabled(By.id("checkout")));
        }
    }

    @Nested
    @DisplayName("When cart has items")
    class CartWithItems {

        @BeforeEach
        void addItemsToCart() {
            // Add test items to cart
        }

        @Test
        @DisplayName("Should show item count")
        void showItemCount() {
            String count = TextBox.getText(By.className("cart-count"));
            assertEquals("2", count);
        }

        @Test
        @DisplayName("Should calculate total correctly")
        void calculateTotal() {
            String total = TextBox.getText(By.className("cart-total"));
            assertEquals("$29.99", total);
        }
    }
}
```

### Conditional Test Execution

```java
import org.junit.jupiter.api.condition.*;

public class PlatformSpecificTest extends BaseTestThreadSafe {

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindows() {
        // Only runs on Windows
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testOnUnix() {
        // Only runs on Linux or Mac
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testOnJava17() {
        // Only runs on Java 17
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "prod")
    void testInProduction() {
        // Only runs if ENV=prod
    }

    @Test
    @DisabledIfSystemProperty(named = "ci", matches = "true")
    void testNotOnCI() {
        // Skipped on CI servers
    }
}
```

### Parallel Execution

```java
import org.junit.jupiter.api.parallel.*;

@Execution(ExecutionMode.CONCURRENT)  // Enable parallel execution
public class ParallelTest extends BaseTestThreadSafe {

    @Test
    @Execution(ExecutionMode.SAME_THREAD)  // Force sequential for this test
    void testSequential() {
        // This test won't run in parallel
    }

    @Test
    void testParallel1() {
        // Runs in parallel with other tests
    }

    @Test
    void testParallel2() {
        // Runs in parallel with other tests
    }
}
```

### Advanced Assertions

```java
import static org.junit.jupiter.api.Assertions.*;

public class AssertionExamplesTest {

    @Test
    void assertionExamples() {
        // Basic assertions
        assertEquals("expected", actual);
        assertNotEquals("unexpected", actual);
        assertTrue(condition);
        assertFalse(condition);
        assertNull(object);
        assertNotNull(object);

        // Array/Collection assertions
        assertArrayEquals(new int[]{1, 2, 3}, actualArray);
        assertIterableEquals(expectedList, actualList);

        // Exception assertions
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("error");
        });

        Exception exception = assertThrows(RuntimeException.class, () -> {
            riskyOperation();
        });
        assertEquals("Expected message", exception.getMessage());

        // Timeout assertions
        assertTimeout(Duration.ofSeconds(2), () -> {
            // Should complete within 2 seconds
            slowOperation();
        });

        // Grouped assertions (all execute, all failures reported)
        assertAll("user",
            () -> assertEquals("John", user.getFirstName()),
            () -> assertEquals("Doe", user.getLastName()),
            () -> assertEquals(30, user.getAge())
        );

        // Custom assertion messages
        assertTrue(condition, () -> {
            // Message is lazily evaluated (only if assertion fails)
            return "Expensive message: " + computeDebugInfo();
        });
    }
}
```

### Using Tags

```java
@Tag("smoke")
@Tag("regression")
public class TaggedTest extends BaseTestThreadSafe {

    @Test
    @Tag("smoke")
    void smokeTest() {
        // Runs with smoke suite
    }

    @Test
    @Tag("regression")
    void regressionTest() {
        // Runs with regression suite
    }
}
```

Run specific tags from Maven:
```bash
mvn test -Dgroups=smoke
mvn test -Dgroups="smoke | regression"
mvn test -DexcludedGroups=slow
```

## Parallel Test Execution Configuration

### In junit-platform.properties

```properties
# Enable parallel execution
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.config.strategy = dynamic
junit.jupiter.execution.parallel.config.dynamic.factor = 1.0
```

### In pom.xml (Maven Surefire)

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.5.2</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```

## Thread Safety Best Practices

1. **Use ThreadLocal Pattern**: Extend `BaseTestThreadSafe` for automatic driver management
2. **Avoid Shared State**: Don't use static fields that can be modified
3. **Use TestContext**: Store test-specific data in `TestContext` (thread-local)
4. **Synchronize When Needed**: Use `@Execution(ExecutionMode.SAME_THREAD)` for tests that can't run in parallel

## Running Tests

### Maven Commands

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LoginTest

# Run specific test method
mvn test -Dtest=LoginTest#testValidLogin

# Run tests with specific tag
mvn test -Dgroups=smoke

# Run tests excluding specific tag
mvn test -DexcludedGroups=slow

# Run tests in parallel
mvn test -Dparallel=methods -DthreadCount=4

# Run tests with verbose output
mvn test -Djunit.platform.console.output.details=VERBOSE
```

### IDE Support

- **IntelliJ IDEA**: Full support for JUnit 5 (built-in)
- **Eclipse**: Install JUnit 5 plugin or use Eclipse 2021.06+
- **VS Code**: Install "Test Runner for Java" extension

## Troubleshooting

### Tests not found

**Problem**: Maven doesn't find tests

**Solution**: Ensure test class names match Surefire patterns:
- `**/Test*.java`
- `**/*Test.java`
- `**/*Tests.java`

### Tests not running in parallel

**Problem**: Tests still run sequentially

**Solution**:
1. Check `junit-platform.properties` has parallel enabled
2. Verify test class is annotated with `@Execution(ExecutionMode.CONCURRENT)`
3. Ensure Maven Surefire is version 3.5.2+

### Parameterized tests not working

**Problem**: `@ParameterizedTest` not recognized

**Solution**: Ensure `junit-jupiter-params` dependency is included

## Additional Resources

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 API Documentation](https://junit.org/junit5/docs/current/api/)
- [Maven Surefire Documentation](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Framework README](README.md)

## Version History

- **v0.0.5** - Updated to JUnit 5.11.3, added parallel execution support
- **v0.0.4** - Wait strategy improvements
- **v0.0.3** - ThreadLocal pattern implementation
- **v0.0.2** - Initial framework version
