package com.api;

import data.DocumentData;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DocumentTest extends BaseTest{

  @Test(groups = "Document.smoke", description = "Create a random document as the QE user.")
  public void testCreateDocument(){
    DocumentData doc = new DocumentData("Test_document_" + RandomStringUtils.randomAlphabetic(4));
    ValidatableResponse response =
    given()
        .when()
        .body(doc)
        .post(API_CONTENT_URI)
        .then()
        .statusCode(201);

    response.body("status", equalTo("published"));
  }

  @Test(groups = "Document.smoke")
  public void testGetDocument(){
    String subject = "Test_document_" + RandomStringUtils.randomAlphabetic(4);
    DocumentData doc = new DocumentData(subject);
    //create the doc and save the returned URI
    String contentURI =
        given()
            .when()
            .body(doc)
            .post(API_CONTENT_URI)
            .then()
            .statusCode(201)
            .extract().path("resources.self.ref");

    //get the doc and check values
    given()
        .when()
        .get(contentURI)
        .then()
        .statusCode(200)
        .body("subject", equalTo(subject),
              "visibility", equalTo("all"),
              "type", equalTo(doc.type));
  }

  @Test(groups = "Document.smoke")
  public void testDeleteDocument(){
    String subject = "Test_document_" + RandomStringUtils.randomAlphabetic(4);
    DocumentData doc = new DocumentData(subject);
    //create the doc and save the returned URI
    String contentURI =
        given()
            .when()
            .body(doc)
            .post(API_CONTENT_URI)
            .then()
            .statusCode(201)
            .extract().path("resources.self.ref");

    //delete and check response
    given()
        .when()
        .delete(contentURI)
        .then()
        .statusCode(204);
  }

  @Test(groups = "Document.smoke")
  public void testUpdateDocument(){
    String subject = "Test_document_" + RandomStringUtils.randomAlphabetic(4);
    DocumentData doc = new DocumentData(subject);
    //create the doc and save the returned URI
    String contentURI =
        given()
            .when()
            .body(doc)
            .post(API_CONTENT_URI)
            .then()
            .statusCode(201)
            .extract().path("resources.self.ref");

    //update the doc and check new values
    doc.subject = "Updated_document_" + RandomStringUtils.randomAlphabetic(4);
    given()
        .when()
        .body(doc)
        .put(contentURI)
        .then()
        .statusCode(200)
        .body("subject", equalTo(doc.subject));
  }

  @Test(groups = "Document.smoke", description = "Expect response time to be under 2 seconds")
  public void testDocumentResponseTime(){
    Long timeout = 2000L;
    String subject = "Test_document_" + RandomStringUtils.randomAlphabetic(4);
    DocumentData doc = new DocumentData(subject);

    //POST
    String contentURI =
        given()
            .body(doc)
            .post(API_CONTENT_URI)
            .then()
            .time(lessThan(timeout))
            .extract().path("resources.self.ref");

    //GET
    given()
        .get(contentURI)
        .then()
        .time(lessThan(timeout));

    //PUT
    given()
        .body(doc)
        .put(contentURI)
        .then()
        .time(lessThan(timeout));

    //DELETE
    given()
        .delete(contentURI)
        .then()
        .time(lessThan(timeout));
  }
}
