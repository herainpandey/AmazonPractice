package com.seleniumapi.suite.utils.screenshotUtils;

import com.seleniumapi.suite.testbase.TestBase;
import com.seleniumapi.suite.Constants.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class SccreenShotUtils extends TestBase {

    public static String takeScreenshot(String methodName, String date) {
        String fileName = new StringBuilder().append("Error_").append(methodName).append("_" + date + "_").append(".jpg").toString();
        String path = Constants.SCREENSHOT_DIRECTORY + "//" + fileName;
        try {
            File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return path;
    }
}
