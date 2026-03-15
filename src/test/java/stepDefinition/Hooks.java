package stepDefinition;

import base.BaseClass;
import io.cucumber.java.After;

public class Hooks extends BaseClass {
    @After(order = 0)
    public void quitBrowser() {
        BaseClass.quitBrowser();
    }
}
