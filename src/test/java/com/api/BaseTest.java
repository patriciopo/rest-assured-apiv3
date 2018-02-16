package com.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.preemptive;

public class BaseTest {
  final String SERVER = System.getProperty("qe.server");
  final String API_CONTENT_URI = SERVER + "/api/core/v3/contents";
  final String API_PEOPLE_URI = SERVER + "/api/core/v3/people";

  @BeforeClass(alwaysRun = true)
  public void setUp(){
    RestAssured.useRelaxedHTTPSValidation();
    RestAssured.defaultParser = Parser.JSON;
    RestAssured.authentication = preemptive().basic(System.getProperty("qe.user"), System.getProperty("qe.pass"));
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //enabling logging of the request and response for all requests if validation fails
  }

}
