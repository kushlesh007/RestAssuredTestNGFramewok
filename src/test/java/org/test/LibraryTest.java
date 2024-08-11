package org.test;

import io.restassured.RestAssured;
import org.example.utilities.RequestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testdata.PayloadTestData;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

public class LibraryTest {

    @Test(dataProvider = "BooksData")
    public void libraryTest(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";

        given()
                .log().all()
                .headers("Content-Type", "application/json")
                .body(RequestUtil.rawToJson(PayloadTestData.libraryPayload(isbn, aisle)))
                .when()
                .log().all()
                .post("/Library/Addbook.php")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();
    }

    @DataProvider(name = "BooksData")
    public Object[][] libraryTestData(){
        return new Object[][] {{"qwe","123"},{"asd","456"},{"zxc","789"}};
    }
}
