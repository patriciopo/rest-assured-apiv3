package com.api;

import data.DocumentData;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DocumentTest extends BaseTest{

  @Test(groups = "debug")
  public void testCreateDocument(){

    DocumentData doc = new DocumentData("Test_document_" + RandomStringUtils.randomAlphabetic(4));
    ValidatableResponse response =
    given()
        .contentType(ContentType.JSON)
        .when()
        .body(doc)
        .post(API_CONTENT_URI)
        .then()
        .statusCode(201);

    response.body("status", equalTo("published"));
  }

}
