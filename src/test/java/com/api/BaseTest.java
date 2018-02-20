package com.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
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

    //default settings
    RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
                                                               .addFilter(new RequestLoggingFilter())   //to log the complete request
                                                               .addFilter(new ResponseLoggingFilter())  //to log the complete response
                                                               .build();

    //set default auth creds to QE user
    RestAssured.authentication = preemptive().basic(System.getProperty("qe.user"), System.getProperty("qe.pass"));

    //enabling logging for all requests/responses only if validation fails
    //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    /*
    //set a default response validation
    ResponseSpecification specification = expect().time(lessThan(2000L));
    RestAssured.responseSpecification = new ResponseSpecBuilder().addResponseSpecification(specification).build();
    */
  }
}
