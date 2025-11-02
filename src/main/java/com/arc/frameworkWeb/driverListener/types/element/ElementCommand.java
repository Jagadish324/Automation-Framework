//package com.arc.frameworkWeb.driverListener.types.element;
//
//import com.arc.frameworkWeb.driverListener.types.BaseCommand;
//import lombok.Getter;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import java.lang.reflect.Method;
//
//@Getter
//public class ElementCommand<T extends WebDriver, U extends WebElement> extends BaseCommand {
//    private T driver;
//    private U element;
//    private By locator;
//
//    public ElementCommand(String id, T driver, U element, By locator, Method method, Object[] arguments) {
//        super(id, method, arguments);
//        this.driver = driver;
//        this.element = element;
//        this.locator = locator;
//    }
//}
