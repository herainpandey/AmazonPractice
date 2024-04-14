package com.seleniumapi.suite.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://bf88ba7b-8264-46f2-8440-a99aa3ff9370.mock.pstmn.io")
                .addHeader("Connection","keep-alive")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();


    }

}
