package org.example.utilities;

import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseUtil {

    public static void validateStatusCode(Response response, int statusCode){
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    public static void validateResponseBody(Response response, String key, Object expectedValue){
        assertThat(response.jsonPath().get(key), equalTo(expectedValue));
    }

    public static <T> void validateResponseBody(Response response, T expectedObject){
        T actualObject = RequestUtil.convertResponseToObject(response, (Class<T>) expectedObject.getClass());
        assertThat(actualObject, equalTo(expectedObject));
    }
}
