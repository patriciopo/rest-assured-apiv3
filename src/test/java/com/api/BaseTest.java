package com.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.preemptive;

public class BaseTest {
  final String API_PATH = "/api/core/v3";
  final String API_CONTENT_URI = "/contents";
  final String API_PEOPLE_URI = "/people";

  @BeforeClass(alwaysRun = true)
  public void setUp(){
    RestAssured.baseURI = System.getProperty("qe.server");
    RestAssured.basePath =  API_PATH;
    RestAssured.useRelaxedHTTPSValidation();
    RestAssured.defaultParser = Parser.JSON;
    RestAssured.authentication = preemptive().basic(System.getProperty("qe.user"), System.getProperty("qe.pass"));
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //enabling logging of the request and response for all requests if validation fails
  }

}
