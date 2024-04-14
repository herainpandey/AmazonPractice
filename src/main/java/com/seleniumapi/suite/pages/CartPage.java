package com.seleniumapi.suite.pages;


import com.seleniumapi.suite.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends AbstractComponent {


    @FindBy(xpath = "//a[contains(text(),'Proceed to checkout')]")
    private WebElement checkoutButton;
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout() throws InterruptedException {
        Thread.sleep(5000);
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    @Override
    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        return checkoutButton.isDisplayed();
    }
}
