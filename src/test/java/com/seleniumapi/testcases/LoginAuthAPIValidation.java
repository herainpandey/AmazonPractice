package com.seleniumapi.testcases;

import com.seleniumapi.suite.api.Route;
import com.seleniumapi.suite.api.applicationapi.AuthAPI;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAuthAPIValidation {

    private String token=null;

    @Test
    public void testAuth() throws IOException {
        token = AuthAPI.getToken(Route.AUTH_RESOURCE);
        System.out.println("this is token " + token);
    }


    @Test(dependsOnMethods = "testAuth" )
    public void getCourses() throws IOException, JSONException {
    String response = AuthAPI.getStudentDetails(Route.COURSE_DETAIL,token);
    JSONObject jsonResponse = new JSONObject(response);
    System.out.println(jsonResponse.getJSONObject("courses").getJSONArray("mobile").get(0));

    }


}
