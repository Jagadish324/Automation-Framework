package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.context.PlaywrightManager;
import com.arc.frameworkWeb.utility.CONSTANT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cookies extends CommonHelper{
    private static Logger log= LogManager.getLogger(Cookies.class.getName());
    /**
     * Deletes all cookies from the current session.
     */
    public static void deleteAllCookies(){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().manage().deleteAllCookies();
        } else {
            PlaywrightManager.getBrowserContext().clearCookies();
        }
    }
    /**
     * Deletes a cookie with the specified name from the current session.
     * @param cookieName The name of the cookie to delete.
     */
    public static void deleteCookieByName(String cookieName){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().manage().deleteCookieNamed(cookieName);
        } else {
            List<com.microsoft.playwright.options.Cookie> allCookies = PlaywrightManager.getBrowserContext().cookies();
            PlaywrightManager.getBrowserContext().clearCookies();
            List<com.microsoft.playwright.options.Cookie> remaining = new ArrayList<>();
            for (com.microsoft.playwright.options.Cookie c : allCookies) {
                if (!c.name.equals(cookieName)) {
                    remaining.add(c);
                }
            }
            if (!remaining.isEmpty()) {
                PlaywrightManager.getBrowserContext().addCookies(remaining);
            }
        }
    }
    /**
     * Deletes a specific cookie from the current session.
     * @param cookieObject The Cookie object representing the cookie to delete.
     */
    public static void deleteCookieObject(Cookie cookieObject){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            getDriver().manage().deleteCookie(cookieObject);
        } else {
            List<com.microsoft.playwright.options.Cookie> allCookies = PlaywrightManager.getBrowserContext().cookies();
            PlaywrightManager.getBrowserContext().clearCookies();
            List<com.microsoft.playwright.options.Cookie> remaining = new ArrayList<>();
            for (com.microsoft.playwright.options.Cookie c : allCookies) {
                if (!c.name.equals(cookieObject.getName())) {
                    remaining.add(c);
                }
            }
            if (!remaining.isEmpty()) {
                PlaywrightManager.getBrowserContext().addCookies(remaining);
            }
        }
    }
    /**
     * Gets all cookies stored in the current session.
     * @return A set containing all cookies as Cookie objects.
     */
    public static Set<Cookie> getAllCookiesObject(){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().manage().getCookies();
        } else {
            log.info("Playwright cookies are accessed via PlaywrightManager.getBrowserContext().cookies(). Returning null for Selenium Cookie set.");
            return null;
        }
    }
    /**
     * Gets all cookies stored in the current session for Playwright.
     * @return A list of Playwright Cookie objects.
     */
    public static List<com.microsoft.playwright.options.Cookie> getAllPlaywrightCookies(){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            log.info("Selenium cookies are accessed via getAllCookiesObject(). Returning null for Playwright Cookie list.");
            return null;
        } else {
            return PlaywrightManager.getBrowserContext().cookies();
        }
    }
    /**
     * Gets a specific cookie by name from the current session.
     * @param cookieName The name of the cookie to retrieve.
     * @return The Cookie object representing the specified cookie, or null if not found.
     */
    public static Cookie getCookieByName(String cookieName){
        if (CONSTANT.TOOL.equalsIgnoreCase("selenium")) {
            return getDriver().manage().getCookieNamed(cookieName);
        } else {
            List<com.microsoft.playwright.options.Cookie> allCookies = PlaywrightManager.getBrowserContext().cookies();
            for (com.microsoft.playwright.options.Cookie c : allCookies) {
                if (c.name.equals(cookieName)) {
                    Cookie seleniumCookie = new Cookie(c.name, c.value, c.domain, c.path, null);
                    return seleniumCookie;
                }
            }
            return null;
        }
    }
}
