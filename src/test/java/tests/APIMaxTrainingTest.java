package tests;

import base.BasePage;
import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.api.Payloads;
import pages.api.ReUsableMethods;
import pages.api.pojo.APIPage;
import pages.api.pojo.GetCoursePage;
import pages.api.pojo.WebAutomationPage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIMaxTrainingTest extends BaseTest {

    /*API tests based on three principles:
    - given - all input details
    - when - submit the API - resource, http methods
    - then - validate the response
    Content of the file to String -> content of the file can convert into Byte -> Byte data to String
    to do that you need: .body(new String(Files.readAllBytes(Paths.get("..."))))*/

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

    @Test
    public void addCommentJiraTest() {

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        /*Login implementation*/
        String response =
                given()
                        .header("Content-Type", "application/json")
                        .body("{ \"username\": \"Admin\", \"password\": \"admin\" }")
                        .log().all()
                        .filter(session)
                        .when()
                        .post("/rest/auth/1/session")
                        .then()
                        .log().all()
                        .extract()
                        .response().asString();

        /*Add comment implementation*/
        given()
                .pathParam("id", "10005")
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"body\": \"This is my first comment!\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(session)
                .when()
                .post("/rest/api/2/issue/{id}/comment")
                .then()
                .log().all()
                .assertThat().statusCode(201);
    }

    @Test
    public void addAttachmentJiraTest() {

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        String response =
                given()
                        .header("Content-Type", "application/json")
                        .body("{ \"username\": \"Admin\", \"password\": \"admin\" }")
                        .log().all()
                        .filter(session)
                        .when()
                        .post("/rest/auth/1/session")
                        .then()
                        .log().all()
                        .extract()
                        .response().asString();

        /*Add attachment implementation*/
        given()
                .header("X-Atlassian-Token", "no-check")
                .filter(session)
                .pathParam("id", "10005")
                .header("Content-Type", "multipart/form-data")
                .multiPart("file", new File("C:\\Users\\Maksim Meleshkin\\JavaProjects\\PersonalProject\\src\\test\\java\\pages\\api\\jira"))
                .when()
                .post("/rest/api/2/issue/{id}/attachments")
                .then()
                .log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void getIssueJiraTest() {

        /*if you have an http website you will need to put this value - .relaxedHTTPSValidation() after the .given()*/

        RestAssured.baseURI = "http://localhost:8080";

        SessionFilter session = new SessionFilter();

        String response =
                given()
                        .header("Content-Type", "application/json")
                        .body("{ \"username\": \"Admin\", \"password\": \"admin\" }")
                        .log().all()
                        .filter(session)
                        .when()
                        .post("/rest/auth/1/session")
                        .then()
                        .log().all()
                        .extract()
                        .response().asString();

        String expectedMessage = "Hi, How are you?";

        /*Add comment implementation*/
        String addCommentResponse = given()
                .pathParam("id", "10005")
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"body\": \"" + expectedMessage + "\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(session)
                .when()
                .post("/rest/api/2/issue/{id}/comment")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract()
                .response().asString();

        JsonPath js = new JsonPath(addCommentResponse);
        String commentId = js.getString("id");

        /*Get Issue implementation*/
        String issueDetails =
                given()
                        .filter(session)
                        .pathParam("id", "10005")
                        .queryParam("fields", "comment")
                        .log().all()
                        .when()
                        .get("/rest/api/2/issue/{id}")
                        .then()
                        .log().all()
                        .extract()
                        .response().asString();

        JsonPath js1 = new JsonPath(issueDetails);
        int commentCount = js1.getInt("fields.comment.comments.size()");
        for (int i = 0; i < commentCount; i++) {
            String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();
            if (commentIdIssue.equalsIgnoreCase(commentId)) {
                String message = js1.get("fields.comment.comments[" + i + "].body").toString();
                Assertions.assertThat(message.equals(expectedMessage));
            }
        }
    }

    public static class APIMaxTrainingPage extends BasePage {
        @FindBy(xpath = "//input[@autocomplete='username']")
        private WebElement usernameField;

        @FindBy(xpath = "//input[@name='password']")
        private WebElement passwordField;

        protected APIMaxTrainingPage(WebDriver driver) {
            super(driver);
        }

        public APIMaxTrainingPage inputValueToUserNameField(String text) {
            input(text, usernameField);
            return this;
        }

        public APIMaxTrainingPage clickEnterAfterFillingUsernameField() {
            clickEnter(usernameField);
            return this;
        }

        public APIMaxTrainingPage inputValueToPasswordField(String text) {
            input(text, passwordField);
            return this;
        }

        public APIMaxTrainingPage clickEnterAfterFillingPasswordField() {
            clickEnter(passwordField);
            return this;
        }
    }

    @Test
    public void oAuthAuthorizationGrantTypeTest() throws InterruptedException {

        String[] coursesTitles = {"Selenium Webdriver Java", "Protractor", "Cypress"};

        /*final String userName = "maksamarskiy@gmail.com";
        final String password = "";

        getDriver().get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

        APIMaxTrainingPage apiMaxTrainingPage = new APIMaxTrainingPage(getDriver())
                .inputValueToUserNameField(userName)
                .clickEnterAfterFillingUsernameField()
                .inputValueToPasswordField(password)
                .clickEnterAfterFillingPasswordField();*/

        String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";

        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);

        String getAccessTokenResponse = given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when()
                .log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(getAccessTokenResponse);
        String accessToken = js.getString("access_token");


        GetCoursePage gc = given()
                .queryParam("access_token", accessToken)
                .expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCoursePage.class);

        System.out.println(gc.getLinkedin());

        /*To get particular course title*/
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        /*To make the previous action dynamic*/
        List<APIPage> apiCources = gc.getCourses().getApi();
        for (int i = 0; i < apiCources.size(); i++) {

            if (apiCources.get(i).equals("SoapUI Webservices testing")) {
                System.out.println(apiCources.get(i).getPrice());
            }
        }

        /*Get Courses name of webAutomation*/
        ArrayList<String> a = new ArrayList<String>();

        List<WebAutomationPage> w = gc.getCourses().getWebAutomation();
        for (int j = 0; j < w.size(); j++) {
            a.add(w.get(j).getCourseTitle());
        }
        List<String> expectedList = Arrays.asList(coursesTitles);

        Assertions.assertThat(a).isEqualTo(expectedList);
    }
}
