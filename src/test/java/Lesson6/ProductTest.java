package Lesson6;

import Lesson5.RetrofitUtils;
import Lesson6.db.model.Products;
import com.github.javafaker.Faker;
import Lesson5.api.ProductService;
import Lesson5.dto.Product;
import okhttp3.ResponseBody;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class ProductTest {

    static ProductService productService;
    Product product = null;
    Product productNew = null;
    Faker faker = new Faker();
    int id;
    String category;
    String title;


    @BeforeAll
    static void beforeAll() throws IOException {
            productService = RetrofitUtils.getRetrofit()
                    .create(ProductService.class);





        Lesson6.db.model.CategoriesExample example = new Lesson6.db.model.CategoriesExample();

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
        title = response.body().getTitle();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), equalTo("Food"));

        SqlSession session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
        Lesson6.db.dao.ProductsMapper productsMapper = session.getMapper(Lesson6.db.dao.ProductsMapper.class);

        Lesson6.db.model.Products selected = (Products) productsMapper.selectByExample(title);

        Assertions.assertEquals(title, selected.getTitle());
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