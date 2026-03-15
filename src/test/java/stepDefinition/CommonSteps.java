package stepDefinition;

import base.BaseClass;
import com.arc.frameworkWeb.helper.*;
import com.arc.frameworkWeb.utility.CONSTANT;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class CommonSteps extends BaseClass {

    @Given("^I launch the browser and hit the URL(.*).$")
    public void launchBrowser(String endPoint) {
        endPoint = endPoint.trim();
        BaseClass.launchSeleniumBrowser();
        Navigate.get(CONSTANT.URL + endPoint);
    }

    @When("I close current window and switch to previous window.")
    public void closeCurrentWindowAndSwitchToPreviousWindow() {
        getDriver().close();
        Window.switchToOriginalWindow();
    }

    @When("I switch to back to default content.")
    public void switchToDefaultContent() {
        Frames.switchToDefaultContent();
    }

    @When("I generate and click on link locator by text {string}.")
    public void generateAndClickOnLink(String visibleText) {
        By locator = LocatorGenerator.generateLinkLocatorFromText(visibleText);
        ExplicitWait.waitForElementsToBeClickable(locator);
        Link.click(locator);
    }

    @Then("Verify and validate alert text as {string} and accept the alert.")
    public void verifyAlertMessage(String expectedMessage) {
        String actualMessage = Alerts.getAlertText();
        Validation.validateText(actualMessage, expectedMessage);
        Alerts.acceptAlert();
    }
}
