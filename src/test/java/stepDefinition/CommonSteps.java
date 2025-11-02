//package stepDefinition;
//
//
//import base.BaseClass;
//import com.arc.frameworkWeb.helper.*;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import com.arc.frameworkWeb.utility.CONSTANT;
//
//public class CommonSteps{
//    private WebDriver driver;
//    @Given("^I launch the browser and hit the URL(.*).$")
//    public void launchBrowser(String endPoint){
//            endPoint =  endPoint.trim();
//            BaseClass baseClass = new BaseClass();
//            if (driver == null) {
//                driver = baseClass.launchBrowser();
//            }
//            driver.get(CONSTANT.URL + endPoint);
//            driver.manage().deleteAllCookies();
//    }
////    @When("^I select \"([^\"]*)\" drop down as \"([^\"]*)\" from(.*).$")
////    public void staticDropDown(String locatorSelect,String dropDownValue, String information){
////        By locator= LocatorSelector.getLocatorBy(locatorSelect);
////        DropDown.selectClassDropDownByVisibleTxt(locator,dropDownValue);
////    }
////    @When("I type in text field {string} as {string}")
////    public void enterDataInTextField(String locatorSelect, String value){
////        By locator = LocatorSelector.getLocatorBy(locatorSelect);
////        TextBox.sendText(locator,value);
////    }
////    @When("I type in text field {string} as {string} and select {string} from dynamic drop down")
////    public void selectDynamicDropDown(String locator, String value, String dropDownValue){
////        By locatorValue = LocatorSelector.getLocatorBy(locator);
////        TextBox.sendText(locatorValue,value);
////    }
////    @When("I click on dynamic drop down {string} option and select {string}")
////    public void clickOnDynamicDropDown(String locatorSelect,String value){
////        By locator = LocatorSelector.getLocatorBy(locatorSelect,value);
////        DropDown.clickOnDropDown(locator);
////    }
////    @When("^I click on button \"([^\"]*)\" and navigate to(.*).$")
////    public void clickOnButton(String locatorSelect, String information){
////        By locatorValue = LocatorSelector.getLocatorBy(locatorSelect);
////        Button.click(locatorValue);
////    }
////    @When("^I click on link \"([^\"]*)\" and navigate to(.*).$")
////    public void clickOnLink(String locatorSelect, String information){
////        By locatorValue = LocatorSelector.getLocatorBy(locatorSelect);
////        Link.click(locatorValue);
////    }
////    @When("I clicked in {string} text box field")
////    public void clickInTextBoxField(String locatorSelect){
////        By locator = LocatorSelector.getLocatorBy(locatorSelect);
////        Button.click(locator);
////    }
//
//    @When("I close current window and switch to previous window.")
//    public void closeCurrentWindowAndSwitchToPreviousWindow(){
//        driver.close();
//        Window.switchToOriginalWindow();
//    }
//
////    @When("I switch to frame {string}.")
////    public void switchFrame(String locatorSelect){
////        By locator = LocatorSelector.getLocatorBy(locatorSelect);
////        Frames.switchToFrame(locator);
////    }
//    @When("I switch to back to default content.")
//    public void switchToDefaultContent(){
//        Frames.switchToDefaultContent();
//    }
//
//    /*
//    Locator Generator
//     */
//    @When("I generate and click on link locator by text {string}.")
//    public void generateAndClickOnLink(String visibleText){
//        By locator = LocatorGenerator.generateLinkLocatorFromText(visibleText);
//        ExplicitWait.waitForElementsToBeClickable(locator);
//        Link.click(locator);
//    }
//
//
////    /*
////    Validation
////     */
////    @Then("^Verify and validate \"([^\"]*)\" from(.*)text as \"([^\"]*)\"$")
////    public void verifyPage(String locatorSelect,String information, String expectedText){
////        By locator = LocatorSelector.getLocatorBy(locatorSelect);
////        String actualText =  ElementInfo.getText(locator);
////        Validation.validateText(actualText,expectedText);
////    }
//
//    @Then("Verify and validate alert text as {string} and accept the alert.")
//    public void verifyAlertMessage(String expectedMessage){
//       String actualMessage =  Alerts.getAlertText();
//       Validation.validateText(actualMessage,expectedMessage);
//       Alerts.acceptAlert();
//    }
//
//}
