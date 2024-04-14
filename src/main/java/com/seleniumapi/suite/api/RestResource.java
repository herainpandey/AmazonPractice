package com.seleniumapi.suite.api;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import com.seleniumapi.suite.utils.Constants.Constants;
import io.restassured.response.Response;
import com.seleniumapi.utils.PropertyReaderUtils.PropertyReader;

import java.io.File;
import java.io.IOException;

import static com.seleniumapi.suite.api.SpecBuilder.getRequestSpec;
import static com.seleniumapi.suite.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response get(String path, String schemafile_path) throws IOException {
        return given(getRequestSpec())
                .when()
                .get(path)
                .then().body(matchesJsonSchema(new File(schemafile_path + "SudemResponseSchema.json")))
                .spec(getResponseSpec())
                .extract().response();

    }

    public static String post(String path) throws IOException {
        return given()
                    .baseUri("https://rahulshettyacademy.com/")
                    .formParam("client_id", PropertyReader.getInstance().getProperty(Constants.AUTH_PROPERTIES,"client_id"))
                    .formParam("client_secret",PropertyReader.getInstance().getProperty(Constants.AUTH_PROPERTIES,"client_secret"))
                    .formParam("grant_type", PropertyReader.getInstance().getProperty(Constants.AUTH_PROPERTIES,"grant_type"))
                    .formParam("scope",PropertyReader.getInstance().getProperty(Constants.AUTH_PROPERTIES,"scope"))
                    .when()
                    .post(path)
                    .then()
                    .extract().response().asString();
}


    public static Response getStudent(String path, String token) throws IOException {
        return given()
                .baseUri("https://rahulshettyacademy.com/")
                .queryParam("access_token", token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();

    }
}
