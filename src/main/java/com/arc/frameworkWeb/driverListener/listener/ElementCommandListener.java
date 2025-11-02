//package com.arc.frameworkWeb.driverListener.listener;
//
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommand;
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommandException;
//import com.arc.frameworkWeb.driverListener.types.element.ElementCommandResult;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//public interface ElementCommandListener<T extends WebDriver, U extends WebElement> {
//
//    default void beforeElementCommandExecuted(ElementCommand<T, U> command) {
//    }
//
//    default void afterElementCommandExecuted(ElementCommandResult<T, U> command) {
//    }
//
//    default void onException(ElementCommandException<T, U> command) {
//    }
//
//}
