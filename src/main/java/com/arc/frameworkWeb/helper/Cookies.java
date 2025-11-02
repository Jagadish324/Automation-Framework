package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class Cookies extends CommonHelper{
    private static Logger log= LogManager.getLogger(Cookies.class.getName());
    /**
     * Deletes all cookies from the current session.
     */
    public static void deleteAllCookies(){
        webDriver.manage().deleteAllCookies();
    }
    /**
     * Deletes a cookie with the specified name from the current session.
     * @param cookieName The name of the cookie to delete.
     */
    public static void deleteCookieByName(String cookieName){
        webDriver.manage().deleteCookieNamed(cookieName);
    }
    /**
     * Deletes a specific cookie from the current session.
     * @param cookieObject The Cookie object representing the cookie to delete.
     */
    public static void deleteCookieObject(Cookie cookieObject){
        webDriver.manage().deleteCookie(cookieObject);
    }
    /**
     * Gets all cookies stored in the current session.
     * @return A set containing all cookies as Cookie objects.
     */
    public static Set<Cookie> getAllCookiesObject(){
        return webDriver.manage().getCookies();
    }
    /**
     * Gets a specific cookie by name from the current session.
     * @param cookieName The name of the cookie to retrieve.
     * @return The Cookie object representing the specified cookie, or null if not found.
     */
    public static Cookie getCookieByName(String cookieName){
        return webDriver.manage().getCookieNamed(cookieName);
    }
}
