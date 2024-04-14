package com.seleniumapi.suite.pages;

import com.seleniumapi.suite.objects.BillingAddress;
import com.seleniumapi.suite.pages.common.AbstractComponent;
import com.seleniumapi.suite.pages.common.CommonHelper;
import com.seleniumapi.suite.utils.Constants.Constants;
import com.seleniumapi.suite.utils.enums.DataEnum;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CheckoutPage extends AbstractComponent {

    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElement> overays;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterBillingDetailsNewUser(BillingAddress billingAddress) throws InterruptedException {

            driver.findElement(By.id("billing_first_name")).sendKeys(billingAddress.getFirstName());
            driver.findElement(By.id("billing_last_name")).sendKeys(billingAddress.getLastName());
            driver.findElement(By.id("billing_company")).sendKeys(billingAddress.getCompany());
            selectFromDropDown(By.id("select2-billing_country-container"), billingAddress.getCountry());
            driver.findElement(By.id("billing_address_1")).sendKeys(billingAddress.getAddressLineOne());
            CommonHelper.enterText(driver.findElement(By.xpath("//input[@id='billing_city']")), billingAddress.getCity(),driver);
            selectFromDropDown(By.id("select2-billing_state-container"), billingAddress.getState());
            driver.findElement(By.id("billing_postcode")).sendKeys(billingAddress.getPostalCode());
            driver.findElement(By.id("billing_email")).sendKeys(billingAddress.getEmail());
        return this;
    }

    public CheckoutPage enterBillingDetailsNewUserExcel(Map<String,String> map) throws InterruptedException {

        driver.findElement(By.id("billing_first_name")).sendKeys(map.get(DataEnum.firstName.toString()));
        driver.findElement(By.id("billing_last_name")).sendKeys(map.get(DataEnum.lastName.toString()));
        driver.findElement(By.id("billing_company")).sendKeys(map.get(DataEnum.company.toString()));
        selectFromDropDown(By.id("select2-billing_country-container"), map.get(DataEnum.country.toString()));
        driver.findElement(By.id("billing_address_1")).sendKeys(map.get(DataEnum.addressLineOne.toString()));
        CommonHelper.enterText(driver.findElement(By.xpath("//input[@id='billing_city']")), map.get(DataEnum.city.toString()),driver);
        selectFromDropDown(By.id("select2-billing_state-container"), map.get(DataEnum.state.toString()));
        driver.findElement(By.id("billing_postcode")).sendKeys(map.get(DataEnum.postalCode.toString()));
        driver.findElement(By.id("billing_email")).sendKeys(map.get(DataEnum.email.toString()));
        return this;
    }

    private void selectFromDropDown(By element,String value) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        WebElement element1 = driver.findElement(By.xpath("//li[text()='"+value+"']"));
        //js.executeScript("arguments[0].scrollIntoView(true);",element1 );
        element1.click();

    }

    public OrderConfirmationPage placeOrder() throws InterruptedException {
        CommonHelper.waitForOverlayToDisapper(overays, wait);
        placeOrderBtn.click();
        return new OrderConfirmationPage(driver);
    }

    public com.seleniumapi.suite.pages.CheckoutPage loginWithUser() throws IOException {
        driver.findElement(By.xpath("//a[contains(text(),'Click here to login')]")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES, "password"));

        driver.findElement(By.cssSelector("#password")).sendKeys(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES, "password"));
        driver.findElement(By.cssSelector("button[class^='woocommerce']")).click();
        return this;
    }

    @Override
    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(placeOrderBtn));
        return placeOrderBtn.isDisplayed();
    }
}

