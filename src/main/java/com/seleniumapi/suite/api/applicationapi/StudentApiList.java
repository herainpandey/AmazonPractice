package com.seleniumapi.suite.api.applicationapi;

import com.seleniumapi.suite.api.RestResource;

import io.restassured.response.Response;

import java.io.IOException;

public class StudentApiList {
    public static Response get(String path, String schemafile_path) throws IOException {
        return RestResource.get(path, schemafile_path);
    }



}
