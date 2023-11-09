package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pages.APIPage;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class APIMaxTrainingTest extends BaseTest {

    /*API tests based on three principles:
    - given - all input details
    - when - submit the API - resource, http methods
    - then - validate the response
    *//*
    first test - validate if API place API is working as expected*/
    @Test
    public void addPlaceTest() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response =
                given().log().all()
                        .queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(APIPage.addPlace())
                        .when().post("maps/api/place/add/json")
                        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                        .header("server", "Apache/2.4.52 (Ubuntu)")
                        .extract().response().asString();

        System.out.println(response);

        /*For parsing JSON*/
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);
    }

    /*Add place -> Update Place with New Address -> Get place to validate if new address is present in response*/
    @Test
    public void updatePlaceWithNewAddressTest() {

    }
}
