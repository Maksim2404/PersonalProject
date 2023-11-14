package tests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.apii.Payloads;
import pages.apii.ReUsableMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIMaxTrainingTest extends BaseTest {

    /*API tests based on three principles:
    - given - all input details
    - when - submit the API - resource, http methods
    - then - validate the response*/
    @Test
    public void addUpdateGetPlaceTest() {

        /*Add Place*/
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response =
                given().log().all()
                        .queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(Payloads.addPlace())
                        .when().post("maps/api/place/add/json")
                        .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                        .header("server", "Apache/2.4.52 (Ubuntu)")
                        .extract().response().asString();

        /*For parsing JSON*/
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");

        /*Update Place with New Address */
        String newAddress = "Austin, Texas";

        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when()
                .put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        /*Get Place*/
        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");

        Assertions.assertThat(newAddress).isEqualTo(actualAddress);
    }

    @Test
    public void howToParseJsonTest() {

         /*1. Print No of courses returned by API
           2.Print Purchase Amount
           3. Print Title of the first course
           4. Print All course titles and their respective Prices
           5. Print no of copies sold by RPA Course
           6. Verify if Sum of all Course prices matches with Purchase Amount*/

        JsonPath js = new JsonPath(Payloads.coursePrice());

        /*Printing number of courses by API*/
        int count = js.getInt("courses.size()");
        System.out.println(count);

        /*Printing purchase amount*/
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        /*Print Title of the first course*/
        String titleNameOfFirstCourse = js.get("courses[0].title");
        System.out.println(titleNameOfFirstCourse);

        /*Print All course titles and their respective Prices*/
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            System.out.println(js.get("courses[" + i + "].price").toString());

            System.out.println(courseTitles);
        }

        /*Print no of copies sold by RPA Course*/
        for (int i = 0; i < count; i++) {

            String courseTitles = js.get("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {

                int copies = js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
    }

    @Test
    public void verifySumAllCoursePricesMatchesWithPurchaseAmountTest() {

        JsonPath js = new JsonPath(Payloads.coursePrice());
        int count = js.getInt("courses.size()");
        int expectedTotalAmount = js.getInt("dashboard.purchaseAmount");
        int sum = 0;

        for (int i = 0; i < count; i++) {

            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            int amount = price * copies;
            sum = sum + amount;
        }
        int actualTotal = sum;

        Assertions.assertThat(expectedTotalAmount).isEqualTo(actualTotal);
    }

    /*Handling Dynamic JSON Payloads with Parameterization*/
    @Test(dataProvider = "BooksData")
    public void addBookTest(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";

        String response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .body(Payloads.addBook(isbn, aisle))
                        .when()
                        .post("/Library/Addbook.php")
                        .then().log().all()
                        .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        /*array - collection of elements
        multidimensional array - collection of arrays*/
        return new Object[][]{{"qwerty", "123"}, {"zxc", "321"}, {"asd", "456"}};
    }
}
