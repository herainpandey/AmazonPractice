package com.seleniumapi.suite.pages.common;

import com.seleniumapi.suite.testbase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbstractComponent {

    protected WebDriverWait wait;

    protected WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver,this);
    }

    public abstract boolean isDisplayed();

}
