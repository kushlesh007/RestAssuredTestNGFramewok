package com.rest.assured;

import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendPayloadAsAFile {

    @Test
    public void sendPayloadAsAFile(){
        File file = new File("src/main/resources/payload/CreateWorkspacePayload.json");

        given()
                .baseUri("https://api.getpostman.com")
                .body(file)
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void sendPayloadUsingMap(){
        Map<String, Object> mainObject = new HashMap<>();

        Map<String, String> nestedObject = new HashMap<>();
        nestedObject.put("name", "map post call");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "test desc post call using map");

        mainObject.put("workspace", nestedObject);

        given()
                .baseUri("https://api.getpostman.com")
                .body(mainObject)
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .post("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
