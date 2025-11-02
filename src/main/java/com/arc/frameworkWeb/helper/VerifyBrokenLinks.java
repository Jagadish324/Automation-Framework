package com.arc.frameworkWeb.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class VerifyBrokenLinks extends CommonHelper {
	private static Logger log= LogManager.getLogger(VerifyBrokenLinks.class.getName());

	String url;
	HttpURLConnection httpURLConnection;
	int responseCode = 200;
	/**
	 * Initializes a VerifyBrokenLinks object with the specified WebDriver.
	 *
	 * @param driver The WebDriver instance to use for verifying links
	 */
	public VerifyBrokenLinks(WebDriver driver) {
		super(driver);
	}

	/**
	 * Verifies all links on the current page and prints their status.
	 */
	public void verfiyLinksOnPage() {
		List<WebElement> links = webDriver.findElements(By.tagName("a"));
//		int size = links.size();
		Iterator<WebElement> value = links.iterator();
		while (value.hasNext()) {

			url = value.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("Link is not present");
			}
			try {
				httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
				httpURLConnection.setRequestMethod("HEAD");
				httpURLConnection.connect();
				responseCode = httpURLConnection.getResponseCode();
				if (responseCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * Verifies all links on the current page and returns the count of broken links.
	 *
	 * @return The count of broken links on the page
	 */
	public int verifyLinksOnPage() {
		int count = 0;
		List<WebElement> links = webDriver.findElements(By.tagName("a"));
//		int size = links.size();
		Iterator<WebElement> value = links.iterator();
		while (value.hasNext()) {

			url = value.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("Link is not present");
			}
			try {
				httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
				httpURLConnection.setRequestMethod("HEAD");
				httpURLConnection.connect();
				responseCode = httpURLConnection.getResponseCode();
				if (responseCode >= 400) {
					System.out.println(url + " is a broken link");
					count++;
				} else {
					System.out.println(url + " is a valid link");
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
}
