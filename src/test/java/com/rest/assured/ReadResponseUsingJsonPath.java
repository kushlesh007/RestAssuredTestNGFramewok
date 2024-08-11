package com.rest.assured;

import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class ReadResponseUsingJsonPath {

    @Test
    public void readResponse(){
        Response res = given()
                .baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")

                //if we don't want to write ifValidationFails() method multiple times
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))

                .when()
                .get("/workspaces")
                .then()

                //logging only failed test case
                //.log().ifError()

                //log if only validation fails
                .log().ifValidationFails()

                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        //1st way to read response using path only
        System.out.println("Response using path only : " + res.path("workspaces[0].name"));

        //2nd way to read response using JsonPath class
        //It accepts only string object
        JsonPath jsonPath = new JsonPath(res.asString());
        String name = jsonPath.getString("workspaces[0].name");
        System.out.println("Response using JsonPath only : " + name);

        //3rd way to read response using JsonPath directly
        System.out.println("Response using JsonPath directly : " +JsonPath.from(res.asString()).getString("workspaces[0].name"));
    }
}
