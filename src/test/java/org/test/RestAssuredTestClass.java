package org.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.example.pojo.GetResponse;
import org.example.utilities.RequestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTestClass {

    String placeId = null;

    private static String getValue(String response, String key){
        JsonPath jsonPath = new JsonPath(response);
        String value = jsonPath.getString(key);
        return value;
    }

    @Test()
    public void restAssuredTestClass() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams("key", "qaclick123")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Frontline house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n")
                .when()
                .log().all()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .assertThat().statusCode(200).extract().response().asString();

        System.out.println(response);

        placeId = RestAssuredTestClass.getValue(response, "place_id");
    }

    @Test(dependsOnMethods = "restAssuredTestClass", groups = {"Regression", "Smoke"})
    public void getRequest() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        GetResponse getResponse = given().log().all()
                .queryParams("key", "qaclick123")
                .queryParams("place_id", placeId)
                .when()
                .log().all()
                .get("/maps/api/place/get/json")
                .then()
                .assertThat()
                .statusCode(200).extract().as(GetResponse.class);

        Assert.assertEquals(getResponse.getAccuracy(), 50, "Not Match");

    }

    @Test(dependsOnMethods = "getRequest")
    public void getRequestWell() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        GetResponse getResponse = RequestUtil.getRequest("/maps/api/place/get/json", "key",
                "qaclick123", "place_id", placeId, GetResponse.class);

        System.out.println(getResponse.getAccuracy());
    }
}