package com.seleniumapi.utils.DateUtils;

public class DateUtil {

    private static volatile DateUtil instance;

    private DateUtil(){

    }

    public static  DateUtil getInstance(){
        if(instance == null) {
            synchronized (DateUtil.class) {
                instance = new DateUtil();
            }
        }
        return instance;
    }
}
