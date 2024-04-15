package com.seleniumapi.suite.utils.Constants;

import org.apache.hc.core5.http.ContentType;

import static org.openqa.selenium.remote.http.HttpClient.Factory.create;

public class Constants {
        public static final String USER_DIR = System.getProperty("user.dir");
        public static final String REPORTS_DIRECTORY = USER_DIR + "//target//test-output//";;
        public static final String SCREENSHOT_DIRECTORY = USER_DIR + "//target//surefire-reports//screenshots";
        public static final String USER_NAME = "root";
        public static final String DB_PASSWORD = "root";
        public static final String DB_URL = "jdbc:mysql://localhost:3306/Business";
        public static final String REMOTE_URL = "http://localhost:4444";
        public static final String REMOTE_CHROME = "chrome";
        public static final String REMOTE_FIREFOX = "firefox";

        public static final String EXCEL_FILE = USER_DIR + "\\src\\main\\resources\\excel\\shoppingData.xlsx";

        public static final String BILLING_ADDRESS_JSON= USER_DIR + "\\src\\main\\resources\\json\\BillingAddress.json";

        public static final String STAGE_PROPERTIES= USER_DIR + "\\src\\main\\resources\\properties\\stage.properties";

        public static final String AUTH_PROPERTIES= USER_DIR + "\\src\\main\\resources\\properties\\authentication.properties";

        public static final String JSON_SCHEMA_PATH = USER_DIR + "\\src\\main\\resources\\json\\";
        public Constants() {

        }
    }
