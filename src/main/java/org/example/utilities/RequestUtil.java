package org.example.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Response getRequest(String endpoint){
        return given()
                .when()
                .get(endpoint);
    }

    public static <T> T getRequest(String endpoint, String querykey1, String queryValue1,
                                   String querykey2, String queryValue2, Class<T> valueType){
        return given()
                .log().all()
                .queryParams(querykey1, queryValue1)
                .queryParams(querykey2, queryValue2)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .extract().response().as(valueType);
    }

    @SneakyThrows
    public static <T> Response postRequest(String endpoint, T body){
        String payload = objectMapper.writeValueAsString(body);
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint);
    }

    @SneakyThrows
    public static <T> T convertResponseToObject(Response response, Class<T> responseType){
        return objectMapper.readValue(response.asString(), responseType);
    }

    @SneakyThrows
    public static <T> List<T> convertResponseToListOfObject(Response response, Class<T> elementType) {
        return objectMapper.readValue(response.asString(), objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
    }

    public static String rawToJson(String response){
        JsonPath jsonPath = new JsonPath(response);
        String jsonString = jsonPath.prettyPrint();
        return jsonString;
    }
}
