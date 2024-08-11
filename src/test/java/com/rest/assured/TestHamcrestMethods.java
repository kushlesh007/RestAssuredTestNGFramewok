package com.rest.assured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestHamcrestMethods {

    @Test
    public void readResponse() {
        given()
                .baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-66b7953c7080720001bdbdaf-61aa849e79eb84e886e024e5fea3dd48d6")
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)

                //contains method
                .body("workspaces.name", contains("Workspace1", "Workspace2", "CollectionDemo", "My Workspace"
                , "Team Workspace", "New Team Workspace", "New Team Workspace"))

                //conatinsInAnyOrder
                .body("workspaces.name", containsInAnyOrder("Workspace2", "Workspace1", "CollectionDemo", "My Workspace"
                        , "Team Workspace", "New Team Workspace", "New Team Workspace"))

                //hasItem
                .body("workspaces.name", hasItem("Workspace2"))

                //hasItems
                .body("workspaces.name", hasItems("Workspace2", "Workspace1"))

                //not(hasItems())
                .body("workspaces.name", not(hasItems("Not present item")))

                //empty
                //.body("workspaces.name", empty());

                //not empty
                 .body("workspaces.name", is(not(empty())))

                //hasSize()
                 .body("workspaces.name", hasSize(7));

    }
}
