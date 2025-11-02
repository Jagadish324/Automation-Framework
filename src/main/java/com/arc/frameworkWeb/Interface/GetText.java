package com.arc.frameworkWeb.Interface;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface GetText {

    String getText(By locator);
    String getText(WebElement locator);
}
