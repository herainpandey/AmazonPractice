package com.seleniumapi.suite.pages.common;

import com.seleniumapi.suite.utils.Constants.Constants;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CommonHelper {

    public static void waitForOverlayToDisapper(List<WebElement> overlay, WebDriverWait wait){
        List<WebElement> overays = overlay;
        if(overays.size() > 0){
            System.out.println("Found Overlay waiting for Overlay to disappear");
            wait.until(ExpectedConditions.invisibilityOfAllElements(overays));
            System.out.println("Overlay is Invisible now");
        }
        else{
            System.out.println("Overlay not found");
        }
    }

    public static void load(String endpoint, WebDriver driver) throws IOException {
        driver.get(PropertyReader.getInstance().getProperty(Constants.STAGE_PROPERTIES, "baseurl"));
    }


    public static String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public static void refersh(WebDriver driver){
        driver.navigate().refresh();
    }

    public static void enterText(WebElement element, String text, WebDriver driver){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().sendKeys(text).build().perform();

    }
}
