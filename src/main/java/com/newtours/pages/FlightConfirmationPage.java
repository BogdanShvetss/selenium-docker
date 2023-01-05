package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightConfirmationPage {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//font[contains(text(),'Flight Confirmation')]")
    private WebElement flightConfirmationHeader;

    @FindBy(xpath = "//td[text()='Total Price']/following::font")
    private WebElement totalPrice;

    @FindBy(linkText = "SIGN-OFF")
    private WebElement signOffLink;

    public FlightConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 5000);
        PageFactory.initElements(driver, this);
    }

    public String getPrice() {
        this.webDriverWait.until(ExpectedConditions.visibilityOf(this.flightConfirmationHeader));
        System.out.println(this.flightConfirmationHeader.getText());
        System.out.println(totalPrice.getText());
        String price = totalPrice.getText();
        this.signOffLink.click();

        return price;
    }
}
