package com.seleniumapi.suite.testbase;

import com.seleniumapi.suite.core.DriverManager;
import com.seleniumapi.suite.utils.Constants.Constants;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class TestBase{

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
    threadDriver.set(driver);
    }

    protected static WebDriver getDriver(){
        return threadDriver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException, URISyntaxException {;
        String browser = PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES,"browser");
        this.setDriver(DriverManager.getInstance().InitializeDriver(browser));
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver(){
       threadDriver.get().quit();
    }

}

