package Lesson3;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class ComplexSearch extends AbstractTest {

    @Test
    void onlyAuth() {
        given()
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json");
    }

    @Test
    void cuisinesValidValue() {
        JsonPath responce = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Irish")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();

        assertThat(responce.get("results[1].title"), equalTo("Corned Beef and Cabbage"));
    }

    @Test
    void minProteine() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minProtein", "10")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body(containsString("Protein"));

    }

    @Test
    void showRecipeNutrition() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("addRecipeNutrition", "true")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body(containsString("nutrition"));

    }
    @Test
    void showIngridients() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("fillIngredients", "true")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body(containsString("usedIngredientCount"))
                .body(containsString("missedIngredientCount"))
                .body(containsString("missedIngredients"));
    }
}
