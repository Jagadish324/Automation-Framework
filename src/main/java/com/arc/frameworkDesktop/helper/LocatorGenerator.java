package com.arc.frameworkDesktop.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LocatorGenerator {
    private static Logger log = LogManager.getLogger(LocatorGenerator.class.getName());

    public static By locatorGeneratorByName(String name){
        return By.xpath("//*[@Name='"+name+"']");
    }
    public static By locatorGeneratorByAutomationIdContains(String id){
        return By.xpath("//*[contains(@AutomationId,'"+id+"')]");
    }
    public static By locatorGeneratorByFullDescriptionContains(String fullDescription){
        return By.xpath("//*[contains(@FullDescription,'"+fullDescription+"')]");
    }

}
