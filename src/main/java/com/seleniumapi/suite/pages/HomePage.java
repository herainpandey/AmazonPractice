package com.seleniumapi.suite.pages;

import com.seleniumapi.suite.pages.common.AbstractComponent;
import com.seleniumapi.suite.Constants.Constants;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class HomePage extends AbstractComponent {

    @FindBy(xpath = "//li[@id='menu-item-1227']/a")
    private WebElement storeMenuLink;

    public HomePage(WebDriver driver) {
        super(driver);

    }

    public HomePage load() throws IOException {
        String baseurl = PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"baseurl");
        driver.get(baseurl);
        return this;
    }
    public StorePage goToStorePage(){
        storeMenuLink.click();
        return new StorePage(driver);
    }

    @Override
    public boolean isDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(storeMenuLink));
        return storeMenuLink.isDisplayed();
    }
}
