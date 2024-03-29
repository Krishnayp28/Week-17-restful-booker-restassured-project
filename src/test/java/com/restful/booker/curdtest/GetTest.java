package com.restful.booker.curdtest;

import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetTest extends TestBase {
    @Test
    public void getAllBookingIDs() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
    }


    @Test
    public void getSingleBookingID() {
        Response response = given()
                .pathParam("id", 10)
                .when()
                .get("{id}");
        response.then().statusCode(200);
    }

    @Test
    public void getPingHealthCheck() {
        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
        response.then()
                .statusCode(201);
    }
}
