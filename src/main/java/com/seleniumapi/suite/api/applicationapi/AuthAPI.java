package com.seleniumapi.suite.api.applicationapi;

import com.seleniumapi.suite.api.RestResource;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;

public class AuthAPI {

    public static String getToken(String path) throws IOException {
        String response = RestResource.post(path);
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getString("access_token");
    }


    public static String getStudentDetails(String path, String token) throws IOException {
        return RestResource.getStudent(path, token).asString();
    }

}
