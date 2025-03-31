package com.arbostar.automation.web.ui.service;

import com.arbostar.automation.web.configuration.ArbostarConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class BrowserStackBaseClient {

    private static final RequestSpecification requestSpecification;

    public static ValidatableResponse sendGet(String endpointUri, String pathParam) {
        return (ValidatableResponse) ((ValidatableResponse) ((Response) RestAssured.given(requestSpecification).when().get(endpointUri, new Object[]{pathParam})).then().statusCode(200));
    }

    public static ValidatableResponse sendGet(String endpointUri) {
        return (ValidatableResponse) ((ValidatableResponse) ((Response) RestAssured.given(requestSpecification).when().get(endpointUri)).then().statusCode(200));
    }

    public static ValidatableResponse sendPut(String endpointUri, Map<String, Object> params, String pathParam) {
        return (ValidatableResponse) ((ValidatableResponse) ((Response) RestAssured.given(requestSpecification).when().contentType(ContentType.JSON).params(params).put(endpointUri, new Object[]{pathParam})).then().statusCode(200));
    }

    static {
        String AUTOMATE_USERNAME = ArbostarConfig.getString("bs.userName");
        String AUTOMATE_ACCESS_KEY = ArbostarConfig.getString("bs.accessKey");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = RestAssured.given().baseUri("https://api.browserstack.com/").auth().basic(AUTOMATE_USERNAME, AUTOMATE_ACCESS_KEY);
    }
}