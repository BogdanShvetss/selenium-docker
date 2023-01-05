package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        String host = "localhost";

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        String completeUrl = "http://" + host + ":4444/wd/hub";
        System.out.println(completeUrl);
        String browser = System.getProperty("BROWSER") == null ? "chrome" : System.getProperty("BROWSER");

        this.driver = getDriver(browser, completeUrl);
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

    public WebDriver getDriver(String driverName, String gridUrl) {
        WebDriver driver = null;

        try {
            switch (driverName.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    //chromeOptions.addArguments("lang=en");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                    break;
                case "firefox":
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setPreference("intl.accept_language", "en");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--disable-dev-shm-usage");
                    firefoxOptions.setProfile(firefoxProfile);
                    driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                    break;
                default:
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("lang=en");
                    driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                    break;
            }
        } catch (MalformedURLException e) {
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
