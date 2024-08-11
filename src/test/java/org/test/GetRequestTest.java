package org.test;

import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.example.pojo.Post;
import org.example.utilities.BaseTest;
import org.example.utilities.ConfigManager;
import org.example.utilities.RequestUtil;
import org.example.utilities.ResponseUtil;
import org.testng.annotations.Test;

import java.io.*;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetRequestTest extends BaseTest {

    @Test
    public void testGetRequest(){
        given()
                .when()
                .get("/posts")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testGetRequest_WellStructured() throws IOException {
        // Send GET request
        Response getResponse = RequestUtil.getRequest(ConfigManager.getProperty("posts.Endpoint"));
        // Assert response status code for GET request
        ResponseUtil.validateStatusCode(getResponse, 200);
        //Print Response body
        System.out.println(getResponse.asString());

        // Deserialize response JSON into Post class
        List<Post> retrievedPost = RequestUtil.convertResponseToListOfObject(getResponse, Post.class);

        //download file
        FileUtils.copyInputStreamToFile(getResponse.getBody().asInputStream(), new File("path"));




    }

}
