package Lesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class Cuisine extends AbstractTest{

    @Test
    void emptyReqest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body(containsString("cuisine"));
    }

}
