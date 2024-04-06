package com.seleniumapi.pages;


import com.seleniumapi.pages.common.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class OrderConfirmationPage extends AbstractComponent {

    @FindBy(css = "p[class^='woocommerce-notice']")
    private WebElement confirmOrderText;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void isOrderConfirmed(){
        Assert.assertEquals(confirmOrderText.getText(),"Thank you. Your order has been received.");
    }

    @Override
    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(confirmOrderText));
        return confirmOrderText.isDisplayed();    }

}
