package org.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.pojo.Post;
import org.example.pojo.PostResponse;
import org.example.utilities.*;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestTest extends BaseTest {

//    @Test
    public void testPostRequest(){
        String requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }

    @Test
    public void testPostRequest_WellStructured(){
        // Load test data
        List<Post> payload = TestDataLoader.loadTestData("post.json", Post.class);

        // Send POST request using RequestUtil
        Response postResponse = RequestUtil.postRequest(ConfigManager.getProperty("posts.Endpoint"), payload);

        // Validate response status code using ResponseUtil
        ResponseUtil.validateStatusCode(postResponse, 201);
    }
}
