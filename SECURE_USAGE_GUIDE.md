# Secure Usage Guide
## Best Practices for Secure Test Automation

This guide provides practical security guidelines for using the Automation Framework safely.

---

## 1. Credential Management

### ❌ DON'T: Hardcode Credentials

```java
// BAD - Never do this!
EmailSender.sendMail("user@example.com", "myuser", "mypassword123", ...);
TextBox.sendKeys(By.id("password"), "hardcoded_password");
```

### ✅ DO: Use Environment Variables

```java
// GOOD - Use environment variables
String username = System.getenv("TEST_USERNAME");
String password = System.getenv("TEST_PASSWORD");

if (username == null || password == null) {
    throw new IllegalStateException("Credentials not configured");
}

TextBox.sendKeys(By.id("username"), username);
TextBox.sendKeys(By.id("password"), password);
```

### ✅ DO: Use Configuration Files (Outside Version Control)

```properties
# config/test-credentials.properties (add to .gitignore!)
test.username=${TEST_USERNAME}
test.password=${TEST_PASSWORD}
api.token=${API_TOKEN}
```

```java
// Load from properties
PropertyFileReader reader = new PropertyFileReader("config/test-credentials.properties");
String username = reader.getData("test.username");
```

### Setting Environment Variables

**Linux/Mac:**
```bash
export TEST_USERNAME=testuser
export TEST_PASSWORD=securepass123
export API_TOKEN=your_token_here
```

**Windows:**
```cmd
set TEST_USERNAME=testuser
set TEST_PASSWORD=securepass123
set API_TOKEN=your_token_here
```

**CI/CD (GitHub Actions):**
```yaml
env:
  TEST_USERNAME: ${{ secrets.TEST_USERNAME }}
  TEST_PASSWORD: ${{ secrets.TEST_PASSWORD }}
```

---

## 2. Secure File Operations

### ❌ DON'T: Use Unvalidated Paths

```java
// BAD - Vulnerable to path traversal
String userInput = "../../../etc/passwd";
ExcelFileReader.getData(userInput, "Sheet1", 0, 0);
```

### ✅ DO: Validate All File Paths

```java
// GOOD - Validate paths
import java.nio.file.*;

public class SecureFileOps {
    private static final Path ALLOWED_DIR = Paths.get("test-data");

    public static Path validatePath(String userPath) {
        Path requested = Paths.get(userPath).normalize();

        // Make absolute
        if (!requested.isAbsolute()) {
            requested = ALLOWED_DIR.resolve(requested).normalize();
        }

        // Check it's within allowed directory
        if (!requested.startsWith(ALLOWED_DIR)) {
            throw new SecurityException("Path not allowed: " + userPath);
        }

        return requested;
    }
}

// Usage
Path safePath = SecureFileOps.validatePath(userInput);
ExcelFileReader.getData(safePath.toString(), "Sheet1", 0, 0);
```

---

## 3. Secure Logging

### ❌ DON'T: Log Sensitive Data

```java
// BAD - Credentials in logs
Log.info("Login with username: {} and password: {}", user, pass);
Log.info("API Token: {}", apiToken);
Log.info("Credit Card: {}", cardNumber);
```

### ✅ DO: Mask Sensitive Data

```java
// GOOD - Mask sensitive data
Log.info("Login with username: {}", user);  // Username might be OK
Log.info("Password length: {}", pass.length());  // Don't log password
Log.info("API Token: {}", maskToken(apiToken));  // Mask token

private static String maskToken(String token) {
    if (token == null || token.length() < 8) {
        return "****";
    }
    return token.substring(0, 4) + "****" + token.substring(token.length() - 4);
}

// Output: "API Token: abcd****xyz123"
```

### ✅ DO: Use Appropriate Log Levels

```java
// Sensitive operations at DEBUG level only
Log.debug("Authentication details: user={}", username);  // OK in debug

// General info at INFO level
Log.info("User logged in successfully");  // No sensitive data
```

---

## 4. Input Validation

### ❌ DON'T: Trust User Input

```java
// BAD - No validation
public void searchProduct(String query) {
    TextBox.sendKeys(By.id("search"), query);  // What if query is null?
}
```

### ✅ DO: Validate All Inputs

```java
// GOOD - Validate inputs
public void searchProduct(String query) {
    if (query == null || query.trim().isEmpty()) {
        throw new IllegalArgumentException("Search query cannot be null or empty");
    }

    if (query.length() > 100) {
        throw new IllegalArgumentException("Search query too long");
    }

    // Sanitize special characters if needed
    String sanitized = query.replaceAll("[<>\"']", "");

    TextBox.sendKeys(By.id("search"), sanitized);
}
```

---

## 5. Secure Command Execution

### ❌ DON'T: Execute Unvalidated Commands

```java
// BAD - Command injection vulnerability
String userScript = getUserInput();
RunPythonCommand.runPythonScript("python", userScript, "data.pdf", "/tmp");
```

### ✅ DO: Whitelist and Validate

```java
// GOOD - Whitelist allowed scripts
public class SecureScriptRunner {
    private static final Set<String> ALLOWED_SCRIPTS = Set.of(
        "process_pdf.py",
        "extract_data.py",
        "generate_report.py"
    );

    private static final Path SCRIPT_DIR = Paths.get("scripts");

    public static String runScript(String scriptName, String dataFile) {
        // Validate script is in whitelist
        if (!ALLOWED_SCRIPTS.contains(scriptName)) {
            throw new SecurityException("Script not allowed: " + scriptName);
        }

        // Validate paths
        Path script = SCRIPT_DIR.resolve(scriptName).normalize();
        if (!script.startsWith(SCRIPT_DIR)) {
            throw new SecurityException("Invalid script path");
        }

        Path data = Paths.get(dataFile).normalize();
        if (data.toString().contains("..")) {
            throw new SecurityException("Path traversal detected");
        }

        return RunPythonCommand.runPythonScript(
            "/usr/bin/python3",  // Fixed command
            script.toString(),
            data.toString(),
            SCRIPT_DIR.toString()
        );
    }
}
```

---

## 6. Secure Test Data Management

### ✅ DO: Use Realistic But Fake Data

```java
// GOOD - Use test data generators
String testEmail = RandomTextGenerator.generateRandomEmail();
String testName = "Test User " + System.currentTimeMillis();
String testCard = "4111111111111111";  // Test card number

// Never use real:
// - Email addresses
// - Credit cards
// - SSNs
// - Phone numbers
// - Personal information
```

### ✅ DO: Clean Up Test Data

```java
@AfterEach
public void cleanup() {
    // Delete test data
    deleteTestUser(testUserId);

    // Clear sensitive data from memory
    password = null;
    apiToken = null;

    // Cleanup driver
    DriverManager.cleanup();
    TestContext.clear();
}
```

---

## 7. Dependency Security

### ✅ DO: Keep Dependencies Updated

```bash
# Check for vulnerabilities
mvn org.owasp:dependency-check-maven:check

# Update dependencies
mvn versions:display-dependency-updates
```

### ✅ DO: Review Dependency Changes

```xml
<!-- Before updating, check changelog -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.25.0</version>  <!-- Know what changed -->
</dependency>
```

---

## 8. Secure CI/CD Configuration

### GitHub Actions Example

```yaml
name: Secure Test Run

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3

      # Use secrets for credentials
      - name: Run Tests
        env:
          TEST_USERNAME: ${{ secrets.TEST_USERNAME }}
          TEST_PASSWORD: ${{ secrets.TEST_PASSWORD }}
          API_TOKEN: ${{ secrets.API_TOKEN }}
        run: mvn test

      # Scan dependencies
      - name: Security Scan
        run: mvn org.owasp:dependency-check-maven:check

      # Don't commit logs with sensitive data
      - name: Clean Logs
        if: always()
        run: |
          find . -name "*.log" -delete
          find . -name "custom.log" -delete
```

---

## 9. Screenshot and Video Security

### ⚠️ WARNING: Screenshots May Contain Sensitive Data

```java
// Be careful with screenshots
@AfterEach
public void takeScreenshot(TestInfo testInfo) {
    if (testFailed) {
        String screenshotPath = ScreenShot.takeScreenshot(testInfo.getDisplayName());

        // ⚠️ Screenshot might contain:
        // - Passwords (if visible on screen)
        // - Credit cards
        // - Personal information
        // - API responses

        // Consider:
        // 1. Blur sensitive fields before capturing
        // 2. Don't upload screenshots to public locations
        // 3. Delete screenshots after analysis
        // 4. Store screenshots in secure locations
    }
}
```

---

## 10. Secure Configuration Checklist

Use this checklist before running tests:

### Pre-Test Security Checklist

- [ ] No hardcoded credentials in code
- [ ] Environment variables configured
- [ ] Credentials file in .gitignore
- [ ] Log files excluded from version control
- [ ] Dependencies scanned for vulnerabilities
- [ ] Input validation implemented
- [ ] Path validation implemented
- [ ] Sensitive data masking enabled
- [ ] Test data is fake (not production)
- [ ] Cleanup procedures in place

### Post-Test Security Checklist

- [ ] Test data cleaned up
- [ ] Logs reviewed for sensitive data
- [ ] Screenshots secured or deleted
- [ ] Temporary files deleted
- [ ] Database cleaned (if applicable)
- [ ] Memory cleared of sensitive data

---

## Common Security Mistakes

### 1. Committing Credentials

```bash
# Add to .gitignore
*.properties
config/credentials.*
.env
*.log
screenshots/
```

### 2. Sharing Logs

```bash
# Before sharing logs, sanitize them
sed -i 's/password=[^&]*/password=****/g' test.log
sed -i 's/token=[^&]*/token=****/g' test.log
```

### 3. Using Production Data

```java
// ❌ NEVER
String prodUsername = "real.user@company.com";

// ✅ ALWAYS
String testUsername = "test.user." + UUID.randomUUID() + "@example.com";
```

---

## Quick Reference

### Secure Code Template

```java
public class SecureTest extends BaseTestThreadSafe {

    private String testUser;
    private String testPass;

    @BeforeEach
    public void setup() {
        super.setup();

        // Load credentials securely
        testUser = System.getenv("TEST_USERNAME");
        testPass = System.getenv("TEST_PASSWORD");

        if (testUser == null || testPass == null) {
            throw new IllegalStateException("Test credentials not configured");
        }

        Log.info("Starting test with user: {}", testUser);
    }

    @Test
    public void secureLoginTest() {
        // Validate inputs
        Objects.requireNonNull(testUser, "Username required");
        Objects.requireNonNull(testPass, "Password required");

        // Navigate securely
        String url = TestContext.getUrl();
        if (!url.startsWith("https://")) {
            Log.warn("Non-HTTPS URL: {}", url);
        }

        Navigate.get(url);

        // Don't log password
        TextBox.sendKeys(By.id("username"), testUser);
        TextBox.sendKeys(By.id("password"), testPass);
        Log.debug("Credentials entered");  // No actual values

        Button.click(By.id("loginButton"));

        // Validate result
        boolean success = Validation.isDisplayed(By.id("dashboard"));
        assert success : "Login failed";
    }

    @AfterEach
    public void cleanup() {
        // Clear sensitive data
        testUser = null;
        testPass = null;

        super.teardown();
    }
}
```

---

## Getting Help

If you discover a security vulnerability:

1. **DO NOT** create a public issue
2. Email security team: [security-team@example.com]
3. Include:
   - Description of vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if available)

---

## Additional Resources

- [OWASP Testing Guide](https://owasp.org/www-project-web-security-testing-guide/)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [CWE Top 25](https://cwe.mitre.org/top25/)
- [Secure Coding Guidelines](https://wiki.sei.cmu.edu/confluence/display/java/SEI+CERT+Oracle+Coding+Standard+for+Java)

---

**Remember: Security is everyone's responsibility! 🔒**
