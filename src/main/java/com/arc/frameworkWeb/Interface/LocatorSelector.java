//package com.arc.frameworkWeb.Interface;
//
//import com.arc.frameworkWeb.helper.LocatorGenerator;
//
//import org.openqa.selenium.By;
//
//
//public class LocatorSelector {
//    private static Object LocatorSelector;
//    public static By getLocatorBy(String locatorValue){
//            try {
//                return (By)LocatorSelector.class.getField(locatorValue).get(LocatorSelector);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//    }
//    public static By getLocatorBy(String locatorValue, String replacedValue){
//        try {
//            return LocatorGenerator.generateLocatorFromString((String)LocatorSelector.class.getField(locatorValue).get(LocatorSelector),replacedValue);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
