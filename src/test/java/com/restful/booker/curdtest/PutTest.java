package com.restful.booker.curdtest;

import com.restful.booker.model.AuthorisationPojo;
import com.restful.booker.model.UpdateBookingPojo;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutTest {

    static ValidatableResponse response;
    static String token;

    @BeforeClass
    public static void inIt() {
        AuthorisationPojo authorisationPojo = new AuthorisationPojo();
        authorisationPojo.setUsername("admin");
        authorisationPojo.setPassword("password123");
        token = given()
                .header("Content-Type", "application/json")
                .when()
                .body(authorisationPojo)
                .post("https://restful-booker.herokuapp.com/auth")
                .then().statusCode(200).extract().path("token");
    }

    @Test
    public void updateCurrentBooking() {

        UpdateBookingPojo.BookingDates date = new UpdateBookingPojo.BookingDates();
        date.setCheckin("2023-06-02");
        date.setCheckout("2023-06-08");
        UpdateBookingPojo updateBookingPojo = new UpdateBookingPojo();
        updateBookingPojo.setFirstname("Bharat");
        updateBookingPojo.setLastname("Patel");
        updateBookingPojo.setTotalprice(200);
        updateBookingPojo.setDepositpaid(true);
        updateBookingPojo.setBookingdates(date);
        updateBookingPojo.setAdditionalneeds("Breakfast and Lunch");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("id", 10)
                .body(updateBookingPojo)
                .when().put("https://restful-booker.herokuapp.com/booking/{id}");
        response.then().log().all().statusCode(200);
    }
}
