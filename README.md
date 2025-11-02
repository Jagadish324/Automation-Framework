# Automation Framework

A comprehensive, enterprise-grade multi-platform test automation framework supporting Web, Mobile (Android/iOS), and Desktop application testing. Designed as a reusable dependency for multiple automation projects.

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Framework Architecture](#framework-architecture)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Framework Modules](#framework-modules)
- [Usage Examples](#usage-examples)
- [Configuration](#configuration)
- [Reporting](#reporting)
- [Code Review](#code-review)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Overview

The **Automation Framework** is a production-ready, modular testing platform that provides a unified interface for automating web browsers, mobile devices, and desktop applications. Built with Java 17+ and Maven, it serves as a foundational dependency for multiple test automation projects across your organization.

### Why Use This Framework?

- **Multi-Platform Support**: Single framework for Web, Mobile (iOS/Android), and Desktop automation
- **Flexible Tool Integration**: Supports Selenium, Playwright, and Appium simultaneously
- **BDD Ready**: Built-in Cucumber support for behavior-driven development
- **Advanced Testing**: OCR, visual testing, image recognition, and API testing capabilities
- **Production Ready**: Comprehensive error handling, retry mechanisms, and professional reporting
- **Dependency Ready**: Package as JAR and use across multiple projects

## Key Features

### Core Capabilities

- **Web Automation**
  - Selenium WebDriver 4.25.0 (Chrome, Firefox, Edge, Safari)
  - Playwright 1.41.1 (Multi-browser support)
  - Advanced wait strategies (Explicit, Auto-wait, Fluent waits)
  - Chrome DevTools integration
  - Browser cookie and local storage management

- **Mobile Automation**
  - Appium 9.2.2 for Android and iOS
  - Native app and mobile browser testing
  - Touch gestures (swipe, tap, pinch, long-press)
  - Device capabilities (orientation, battery, location, SMS)
  - Toast message handling

- **Desktop Automation**
  - Windows/Mac/Linux desktop application support
  - System-level interactions
  - Server command execution

- **Advanced Features**
  - **Visual Testing**: Screenshot capture, image comparison, pixel-level diffing
  - **OCR (Tesseract)**: Text extraction from images and PDFs
  - **Image Recognition (SikuliX)**: Image-based element identification
  - **Data-Driven Testing**: Excel (POI) and PDF (PDFBox) data processing
  - **API Testing**: REST Assured integration for API validation
  - **Email Verification**: SMTP and SendGrid integration
  - **Accessibility Testing**: Built-in accessibility validation helpers

- **Testing Frameworks**
  - Cucumber 7.16.1 (BDD with Gherkin)
  - JUnit 5 (Platform, Jupiter, Suite)
  - Cucumber-JUnit integration

- **Reporting & Logging**
  - ExtentReports 5.0.9 (HTML reports)
  - ReportPortal integration
  - Log4j 2.21.1 (File and console logging)
  - Thread-safe logging with method context

## Framework Architecture

```
Automation-Framework/
├── src/
│   ├── main/java/com/arc/
│   │   ├── frameworkWeb/          # Web automation (140+ methods)
│   │   │   ├── helper/            # Button, TextBox, DropDown, etc.
│   │   │   ├── autowait/          # Auto-wait plugin system
│   │   │   ├── driverListener/    # Event listener pattern
│   │   │   └── utility/           # Constants and utilities
│   │   │
│   │   ├── frameworkDevice/       # Mobile automation (100+ methods)
│   │   │   ├── helper/            # Android/iOS helpers
│   │   │   └── utility/           # Mobile constants
│   │   │
│   │   ├── frameworkDesktop/      # Desktop automation
│   │   │   ├── helper/            # Desktop interaction helpers
│   │   │   └── utility/           # Desktop constants
│   │   │
│   │   └── helper/                # Shared utilities (23 classes)
│   │       ├── ExcelFileReader.java
│   │       ├── PDFReader.java
│   │       ├── OCR.java
│   │       ├── SikuliClass.java
│   │       ├── Email.java
│   │       └── Log.java
│   │
│   └── test/
│       ├── java/
│       │   ├── base/              # Base test classes
│       │   ├── stepDefinition/    # Cucumber step definitions
│       │   └── testRunner/        # Test runner configurations
│       └── resources/
│           └── featureFile.feature # BDD feature files
│
├── pom.xml                         # Maven dependencies
└── src/main/resources/log4j2.xml  # Logging configuration
```

### Design Patterns

- **Helper Pattern**: 100+ reusable helper classes for common operations
- **Page Object Model Ready**: Structure supports POM implementation
- **Event Listener Pattern**: SpyDriver for monitoring driver interactions
- **Factory Pattern**: Driver initialization for different platforms
- **Singleton Pattern**: Shared utility instances

## Technology Stack

### Core Technologies

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Java | 17+ |
| **Build Tool** | Maven | 3+ |
| **Web Automation** | Selenium WebDriver | 4.25.0 |
| **Web Automation** | Playwright | 1.41.1 |
| **Mobile Automation** | Appium Java Client | 9.2.2 |
| **BDD Framework** | Cucumber | 7.16.1 |
| **Test Framework** | JUnit 5 | 5.10.2 |
| **API Testing** | REST Assured | 5.4.0 |
| **Logging** | Log4j 2 | 2.21.1 |
| **Reporting** | ExtentReports | 5.0.9 |
| **Reporting** | ReportPortal | 5.3.1 |
| **Data Processing** | Apache POI | 5.2.3 |
| **PDF Processing** | Apache PDFBox | 3.0.2 |
| **OCR** | Tesseract (Tess4J) | 5.11.0 |
| **Image Processing** | OpenCV | 4.9.0 |
| **Visual Automation** | SikuliX | 2.0.5 |
| **Email** | SendGrid | 4.10.2 |
| **Email** | Jakarta Mail | 2.0.1 |
| **HTTP Client** | OkHttp | 4.12.0 |
| **Database** | SQLite JDBC | 3.44.1.0 |
| **Spring** | Spring Framework | 6.1.5 |

## Prerequisites

### System Requirements

- **Java Development Kit (JDK)**: Version 17 or higher
- **Maven**: Version 3.6+ for dependency management
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (with Java extensions)

### Additional Requirements by Module

**For Web Automation:**
- Web browsers (Chrome, Firefox, Edge, Safari)
- WebDrivers are managed automatically by Selenium 4

**For Mobile Automation:**
- Appium Server 2.x
- Android SDK (for Android testing)
- Xcode (for iOS testing on Mac)
- Physical devices or emulators/simulators

**For OCR and Visual Testing:**
- Tesseract OCR engine installed on system
- OpenCV native libraries (bundled via JavaCV)

## Installation

### As a Dependency in Your Project

1. **Build and Install Framework**

```bash
# Clone the framework repository
git clone <repository-url>
cd Automation-Framework

# Build and install to local Maven repository
mvn clean install
```

2. **Add Dependency to Your Project**

```xml
<!-- In your project's pom.xml -->
<dependency>
    <groupId>com.arc</groupId>
    <artifactId>AutomationFramework</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### For Framework Development

```bash
# Clone the repository
git clone <repository-url>
cd Automation-Framework

# Download dependencies
mvn clean install

# Import into your IDE as Maven project
```

## Framework Modules

### 1. frameworkWeb (Web Automation)

The web module provides 140+ methods for browser automation using Selenium or Playwright.

**Key Helper Classes:**

- **CommonHelper**: Base helper with element retrieval and utilities
- **Button**: Click operations with retry logic and exception handling
- **TextBox**: Text input with clear, type, and validation methods
- **DropDown**: Select by value, index, visible text
- **CheckBox**: Check, uncheck, toggle operations
- **Link**: Link navigation and validation
- **Mouse**: Hover, drag-and-drop, context-click
- **KeyBoard**: Key press, combinations, shortcuts
- **Alerts**: Alert accept, dismiss, text retrieval
- **Frames**: Frame switching and navigation
- **Window**: Window/tab management
- **Navigate**: Browser navigation (back, forward, refresh)
- **Cookies**: Cookie management operations
- **JavaScriptExecutor**: Execute custom JavaScript
- **ScreenShot**: Capture and compare screenshots
- **ExplicitWait**: Custom wait conditions
- **AutoWait**: Automatic wait mechanism before actions
- **Validation**: Element state validation
- **ChromeDevTools**: Network and console log capture
- **VerifyBrokenLinks**: Link validation across pages
- **Accessibility**: Accessibility testing helpers
- **Download/Upload**: File operations

**Usage Example:**

```java
import com.arc.frameworkWeb.helper.*;
import org.openqa.selenium.By;

// Click a button with auto-wait and retry
Button.click(By.id("submitButton"));

// Type in text box
TextBox.sendKeys(By.name("username"), "testuser");

// Select from dropdown
DropDown.selectByVisibleText(By.id("country"), "United States");

// Validate element is displayed
boolean isDisplayed = Validation.isDisplayed(By.xpath("//h1[@class='title']"));
```

### 2. frameworkDevice (Mobile Automation)

The device module provides 100+ methods for Android and iOS mobile automation.

**Key Helper Classes:**

- **AndroidDriverCap**: Android driver setup and capabilities
- **IosDriver**: iOS driver initialization
- **DeviceInfo**: Device information retrieval
- **GestureAction**: Swipe, tap, pinch, long-press gestures
- **Orientation**: Change device orientation
- **Battery**: Monitor battery status
- **GeoLocation**: Set GPS location for testing
- **ToastMessage**: Toast notification handling
- **AppConfig**: App installation, termination, reset
- Plus mobile versions of Button, TextBox, etc.

**Usage Example:**

```java
import com.arc.frameworkDevice.helper.*;
import io.appium.java_client.AppiumBy;

// Initialize Android driver
AndroidDriverCap.androidDriver();

// Perform swipe gesture
GestureAction.swipeUp();

// Scroll to element
AndroidDriverCap.androidScrollToElement("Settings");

// Get device battery info
Battery.getBatteryLevel();

// Set location
GeoLocation.setLocation(37.7749, -122.4194); // San Francisco
```

### 3. frameworkDesktop (Desktop Automation)

Desktop application automation for Windows, Mac, and Linux applications.

**Key Helper Classes:**

- **AppConfig**: Desktop app configuration
- **Image**: Image-based element recognition
- **SystemInfo**: System information retrieval
- **ServerAction**: Server-side command execution
- Plus desktop versions of common helpers

### 4. helper (Shared Utilities)

Cross-framework utilities available to all modules.

**Key Helper Classes:**

**Data Processing:**
- **ExcelFileReader**: Read/write Excel files (.xls, .xlsx)
- **PDFReader**: Extract text and metadata from PDFs
- **PropertyFileReader**: Properties file management

**Advanced Capabilities:**
- **OCR**: Extract text from images using Tesseract
- **SikuliClass**: Image-based automation
- **VisualActions**: Visual testing and comparison
- **Email**: SMTP email verification
- **EmailSender**: Send emails via SendGrid
- **MobileSMS**: SMS verification

**Utilities:**
- **Log**: Thread-safe logging with method context
- **DateAndTime**: Date/time operations
- **RandomTextGenerator**: Generate random test data
- **StringTypeCheck**: String validation utilities
- **Sorting**: Data sorting helpers
- **RequestResponseLogger**: HTTP logging
- **RunPythonCommand**: Execute Python scripts from Java

**Usage Examples:**

```java
import com.arc.helper.*;

// Read from Excel
String data = ExcelFileReader.getData("testdata.xlsx", "Sheet1", 1, 0);

// Extract text from image using OCR
String text = OCR.extractText("screenshot.png");

// Thread-safe logging with method context
Log.info("Test step completed successfully");

// Generate random email
String email = RandomTextGenerator.generateRandomEmail();

// Read PDF content
String pdfText = PDFReader.extractText("document.pdf");
```

## Usage Examples

### Complete Web Test Example

```java
import com.arc.frameworkWeb.helper.*;
import com.arc.helper.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static void main(String[] args) {
        // Initialize driver
        WebDriver driver = new ChromeDriver();
        CommonHelper commonHelper = new CommonHelper(driver);

        try {
            // Navigate to URL
            Navigate.get("https://example.com/login");
            Log.info("Navigated to login page");

            // Enter credentials
            TextBox.sendKeys(By.id("username"), "testuser");
            TextBox.sendKeys(By.id("password"), "password123");
            Log.info("Entered credentials");

            // Click login button
            Button.click(By.id("loginButton"));
            Log.info("Clicked login button");

            // Validate successful login
            boolean isLoggedIn = Validation.isDisplayed(By.className("dashboard"));
            Log.info("Login validation: " + isLoggedIn);

            // Take screenshot
            ScreenShot.takeScreenshot("login_success");

        } finally {
            driver.quit();
        }
    }
}
```

### Cucumber BDD Test Example

**Feature File** (`src/test/resources/login.feature`):

```gherkin
Feature: User Login

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "testuser"
    And I enter password "password123"
    And I click the login button
    Then I should see the dashboard
```

**Step Definitions**:

```java
import com.arc.frameworkWeb.helper.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class LoginSteps {

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        Navigate.get("https://example.com/login");
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        TextBox.sendKeys(By.id("username"), username);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        TextBox.sendKeys(By.id("password"), password);
    }

    @When("I click the login button")
    public void clickLogin() {
        Button.click(By.id("loginButton"));
    }

    @Then("I should see the dashboard")
    public void validateDashboard() {
        assert Validation.isDisplayed(By.className("dashboard"));
    }
}
```

### Data-Driven Test Example

```java
import com.arc.helper.ExcelFileReader;
import com.arc.frameworkWeb.helper.*;

public class DataDrivenTest {

    public void testMultipleUsers() {
        // Read test data from Excel
        Object[][] testData = ExcelFileReader.getExcelData(
            "testdata.xlsx", "Users"
        );

        // Iterate through test data
        for (Object[] row : testData) {
            String username = (String) row[0];
            String password = (String) row[1];

            // Execute test with data
            TextBox.sendKeys(By.id("username"), username);
            TextBox.sendKeys(By.id("password"), password);
            Button.click(By.id("loginButton"));

            // Validate and reset
            // ...
        }
    }
}
```

## Configuration

### Log4j Configuration

Edit `src/main/resources/log4j2.xml` to customize logging:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="File" fileName="custom.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```

### Framework Constants

Configure framework behavior via utility classes:

**Web Framework** (`com.arc.frameworkWeb.utility.CONSTANT`):
- `TOOL`: "selenium" or "playwright"
- `BROWSER_TYPE`: Chrome, Firefox, Edge, Safari
- `IMPLICIT_WAIT`: Default implicit wait time
- `EXPLICIT_WAIT`: Default explicit wait time

**Device Framework** (`com.arc.frameworkDevice.utility.CONSTANT`):
- `DEVICE_NAME`: Name of the device/emulator
- `APP_LOCATION`: Path to APK/IPA file
- `PLATFORM`: Android or iOS

### Cucumber Configuration

Configure Cucumber in `pom.xml`:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
    <configuration>
        <properties>
            <configurationParameters>
                cucumber.junit-platform.naming-strategy=long
            </configurationParameters>
        </properties>
    </configuration>
</plugin>
```

## Reporting

### ExtentReports

ExtentReports generates HTML reports automatically when using the Cucumber adapter.

**Configuration** (create `extent.properties` in `src/test/resources`):

```properties
extent.reporter.spark.start=true
extent.reporter.spark.out=test-output/SparkReport/Spark.html
extent.reporter.spark.config=src/test/resources/extent-config.xml

screenshot.dir=test-output/screenshots/
screenshot.rel.path=../screenshots/

basefolder.name=test-output
basefolder.datetimepattern=d-MMM-YY HH-mm-ss
```

### ReportPortal Integration

ReportPortal provides real-time test execution monitoring and AI-powered analytics.

**Configuration** (create `reportportal.properties`):

```properties
rp.endpoint=http://localhost:8080
rp.uuid=your-uuid-here
rp.launch=automation-framework-tests
rp.project=default_personal
```

### Custom Logging

Use the framework's Log class for consistent logging:

```java
import com.arc.helper.Log;

Log.info("Test started");
Log.debug("Debug information: {}", variable);
Log.warn("Warning message");
Log.err("Error occurred");
Log.fatal("Critical failure");
```

**Log Output Format:**
```
[methodName] Your log message
```

## Code Review

### Strengths

1. **Modular Architecture**: Clean separation between Web, Mobile, and Desktop frameworks
2. **Comprehensive Coverage**: 140+ helper methods covering most automation scenarios
3. **Error Handling**: Built-in retry logic for common exceptions (ElementClickInterceptedException)
4. **Thread-Safe Logging**: ConcurrentHashMap-based logger caching with method context
5. **Dual Tool Support**: Seamless switching between Selenium and Playwright
6. **Extensibility**: Helper pattern makes it easy to add new capabilities
7. **Documentation**: Javadoc comments on most methods
8. **Modern Java**: Uses Java 17+ features and best practices
9. **Auto-Wait Mechanism**: Reduces flakiness with built-in waits
10. **Production Ready**: Used as dependency across multiple projects

### Areas for Improvement

1. **Static Context**: Extensive use of static methods may complicate:
   - Parallel test execution
   - Test isolation
   - Mocking for unit tests
   - **Recommendation**: Consider instance-based approach or ThreadLocal pattern

2. **Resource Management**: Some methods don't use try-with-resources
   - File streams in ExcelFileReader need better cleanup
   - **Recommendation**: Use try-with-resources for all AutoCloseable resources

3. **Error Handling Consistency**: Mixed approach to exception handling
   - Some methods use printStackTrace()
   - Others throw exceptions
   - **Recommendation**: Standardize on logging and re-throwing wrapped exceptions

4. **Code Cleanliness**: Commented-out code throughout the codebase
   - Old implementations left in comments
   - **Recommendation**: Remove commented code (use version control for history)

5. **Magic Numbers**: Some hardcoded values without constants
   - Wait times, retry counts
   - **Recommendation**: Extract to constants or configuration

6. **Null Safety**: Missing null checks in critical paths
   - CommonHelper.getListFromCommaSeparatedString handles null but others don't
   - **Recommendation**: Add null checks or use Optional

7. **Method Naming**: Some inconsistencies
   - `err()` vs `error()`
   - **Recommendation**: Follow standard naming conventions

8. **Dependency Management**: Some version conflicts
   - Exclusions needed for Playwright and SikuliX
   - **Recommendation**: Review and update to compatible versions

9. **Test Coverage**: Framework code lacks unit tests
   - **Recommendation**: Add unit tests for utility classes

10. **Configuration**: Hardcoded configuration in utility classes
    - **Recommendation**: Move to external properties files

### Recommendations for Projects Using This Framework

1. **Thread Safety**: Use ThreadLocal for WebDriver instances
2. **Page Object Model**: Implement POM on top of these helpers
3. **Test Data Management**: Externalize test data to Excel/JSON/YAML
4. **Environment Config**: Use property files for environment-specific settings
5. **Parallel Execution**: Configure Maven Surefire for parallel test runs
6. **CI/CD Integration**: Add Jenkins/GitHub Actions pipeline configuration

## Best Practices

### 1. Use Page Object Model

```java
public class LoginPage {
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginButton");

    public void login(String username, String password) {
        TextBox.sendKeys(usernameField, username);
        TextBox.sendKeys(passwordField, password);
        Button.click(loginButton);
    }
}
```

### 2. Implement Base Test Class

```java
public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        new CommonHelper(driver);
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

### 3. Use Meaningful Logging

```java
Log.info("Starting login test for user: {}", username);
Log.debug("Current URL: {}", driver.getCurrentUrl());
```

### 4. Handle Waits Appropriately

```java
// Let framework handle waits automatically
Button.click(locator);

// Or use explicit waits when needed
ExplicitWait.waitForElementToBeClickable(locator, 10);
```

### 5. Externalize Test Data

```java
// testdata.xlsx
Object[][] users = ExcelFileReader.getExcelData("testdata.xlsx", "Users");
```

### 6. Capture Evidence

```java
ScreenShot.takeScreenshot("test_failure_" + testName);
```

## Contributing

### Setting Up Development Environment

```bash
# Fork and clone
git clone <your-fork-url>
cd Automation-Framework

# Create feature branch
git checkout -b feature/your-feature-name

# Make changes and test
mvn clean test

# Commit and push
git add .
git commit -m "Add feature: description"
git push origin feature/your-feature-name
```

### Coding Standards

- Follow Java naming conventions
- Add Javadoc for all public methods
- Include unit tests for new features
- Keep methods focused and single-purpose
- Use meaningful variable names
- Add logging for important operations

### Pull Request Process

1. Update documentation for new features
2. Add usage examples
3. Ensure all tests pass
4. Update CHANGELOG.md
5. Request review from maintainers

## Troubleshooting

### Common Issues

**Issue**: WebDriver not found
```bash
Solution: Selenium 4+ manages drivers automatically. Ensure internet connection for first run.
```

**Issue**: Appium connection refused
```bash
Solution: Start Appium server before running mobile tests
appium --allow-cors
```

**Issue**: OCR not working
```bash
Solution: Install Tesseract OCR engine
# macOS
brew install tesseract

# Ubuntu
sudo apt-get install tesseract-ocr

# Windows
Download from: https://github.com/UB-Mannheim/tesseract/wiki
```

**Issue**: Parallel execution failures
```bash
Solution: Use ThreadLocal for WebDriver instances in your test classes
```

**Issue**: Maven dependency conflicts
```bash
Solution: Run dependency tree analysis
mvn dependency:tree
```

### Debug Mode

Enable debug logging in `log4j2.xml`:

```xml
<Root level="debug">
```

### Getting Help

- Check Javadoc in the code
- Review usage examples in this README
- Check framework logs in `custom.log`
- Contact framework maintainers

## License

[Specify your license here - MIT, Apache 2.0, Proprietary, etc.]

## Maintainers

[Add maintainer information]

## Version History

### 0.0.1-SNAPSHOT (Current)
- Initial release
- Web automation with Selenium and Playwright
- Mobile automation with Appium
- Desktop automation support
- OCR and visual testing capabilities
- Cucumber BDD integration
- ExtentReports and ReportPortal integration

---

**Built with ❤️ for the automation community**
