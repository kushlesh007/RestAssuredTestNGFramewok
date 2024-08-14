package com.rest.assured;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class PostPutAndDeleteRequestTest {

    @Test
    public void postCallTest(){
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"Post call created Workspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured created this\"\n" +
                "    }\n" +
                "}";

        Response response = given()
                .baseUri("https://api.getpostman.com")
                .body(payload)
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspace.name", equalTo("Post call created Workspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"))
                .extract().response();
    }

    @Test
    public void putRequestTest(){
        String workspaceId = "6f9a07d3-7146-4f77-ac93-b7dae698bb82";

        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"Post call created Workspace updated test\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured created this updated\"\n" +
                "    }\n" +
                "}";

        Response response = given()
                .baseUri("https://api.getpostman.com")
                .body(payload)
                .pathParam("workspaceId", workspaceId)
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .put("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspace.name", equalTo("Post call created Workspace updated test"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId))
                .extract().response();
    }

    @Test
    public void deleteRequestTest(){
        String workspaceId = "97f95f5e-89e5-4f08-9b58-a4db32a32fcf";

        Response response = given()
                .baseUri("https://api.getpostman.com")
                .pathParam("workspaceId", workspaceId)
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .delete("/workspaces/{workspaceId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId))
                .extract().response();
    }
}
