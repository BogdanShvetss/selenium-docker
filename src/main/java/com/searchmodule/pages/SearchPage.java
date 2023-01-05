package com.searchmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchTextBox;

    @FindBy(id = "search_button_homepage")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'tile--vid')]")
    private List<WebElement> allVideos;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void goTo() {
        this.webDriver.get("https://duckduckgo.com");
    }

    public void doSearch(String keyword) {
        this.wait.until(ExpectedConditions.visibilityOf(this.searchTextBox));
        this.searchTextBox.sendKeys(keyword);
        this.searchButton.click();
    }

    public void goToVideos() {
        this.wait.until(ExpectedConditions.visibilityOf(this.videosLink));
        this.videosLink.click();
    }

    public int getResult() {
        By videos = By.cssSelector("div[class*='tile--vid']");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(videos, 0));
        System.out.println(this.allVideos.size());

        return this.allVideos.size();
    }
}

