package Lesson6;

import Lesson5.RetrofitUtils;
import com.github.javafaker.Faker;
import Lesson5.api.ProductService;
import Lesson5.dto.Product;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class ProductTest {

    static ProductService productService;
    Product product = null;
    Product productNew = null;
    Faker faker = new Faker();
    int id;
    String category;


    @BeforeAll
    static void beforeAll() {
        try {
            productService = RetrofitUtils.getRetrofit()
                    .create(ProductService.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 1000));
    }



    @DisplayName("Creates a new product - POST")
    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));
    }


    @DisplayName("Modify product - PUT")
    @Test
    void modifyCreatedProductTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        category = response.body().getCategoryTitle();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        productNew = new Product()
                .withId(id)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(category)
                .withPrice((int) (Math.random() * 1000));


        Response<Product> modifyResponce = productService.modifyProduct(productNew)
                .execute();
        assertThat(modifyResponce.isSuccessful(), CoreMatchers.is(true));
        assertThat(modifyResponce.body().getId(), equalTo(id));
        assertThat(modifyResponce.body().getCategoryTitle(), equalTo(category));

    }

    @DisplayName("Returns a specific product by their identifier - GET")
    @Test
    void getProductById() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));


        Response<Product> getIdResponce = productService.getProductById(id)
                .execute();
        assertThat(getIdResponce.isSuccessful(), CoreMatchers.is(true));
        assertThat(getIdResponce.body().getId(), equalTo(id));
        assertThat(getIdResponce.body().getCategoryTitle(), equalTo("Food"));

    }
}