package Lesson4;

import Lesson4.GetResp.GetResponse;
import Lesson4.GetResp.Item;
import Lesson4.PostResp.AddToShoppingListRequest;
import Lesson4.PostResp.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingListTest extends AbstractTest {
    private  final String USER_NAME = "test262";

    @Test
    void addItemToShoppingList() {

        Response response = given()
                .spec(getRequestSpecification())
                .when()
                .body(new AddToShoppingListRequest("Sesame Flank Steak Salad", "Salad", true))
                .post(getBaseUrl() +"mealplanner/" + USER_NAME + "/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getName(), containsString("sesame flank steak salad"));
        assertThat(response.getAisle(),containsString("Salad"));
    }

    @Test
    void getItemFromShoppingList() {
        Item response = given()
                .spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() +"mealplanner/" + USER_NAME + "/shopping-list")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(Item.class);
        assertThat(response.getName(), containsString("sesame flank steak salad"));
        assertThat(response.getAisle(), containsString("Salad"));

    }
}
