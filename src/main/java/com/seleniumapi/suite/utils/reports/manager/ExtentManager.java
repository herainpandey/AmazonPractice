package com.seleniumapi.suite.utils.reports.manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.seleniumapi.suite.Constants.Constants;

import java.io.File;

public class ExtentManager {
        private static final ExtentReports extentReport = new ExtentReports();
        private static synchronized ExtentReports createInstance(){
            //File Name
            String fileName = new StringBuilder().append("Report").append(".html").toString();

            //Layout of Report
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(Constants.REPORTS_DIRECTORY + File.separator +fileName);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Run");
            sparkReporter.config().setEncoding("UTF-8");
            sparkReporter.config().setReportName(fileName);

            //Initialise ExtentReport
            extentReport.setSystemInfo("Organization","Hellooo");
            extentReport.setSystemInfo("Automation Framework","Selenium WebDriver");
            extentReport.attachReporter(sparkReporter);

            return extentReport;
        }

        public static ExtentReports getInstance(){
            return createInstance();
        }
    }
