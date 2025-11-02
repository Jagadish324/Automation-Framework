//package com.arc.frameworkWeb.driverListener.listener;
//
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommand;
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommandException;
//import com.arc.frameworkWeb.driverListener.types.driver.DriverCommandResult;
//import org.openqa.selenium.WebDriver;
//
//public interface DriverCommandListener<T extends WebDriver> {
//
//    default void beforeDriverCommandExecuted(DriverCommand<T> command) {
//    }
//
//    default void afterDriverCommandExecuted(DriverCommandResult<T> command) {
//    }
//
//    default void onException(DriverCommandException<T> command) {
//    }
//
//}
