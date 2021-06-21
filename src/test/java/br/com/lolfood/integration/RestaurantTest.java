package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantTest {

    private String BASE_PATH = "/restaurant";

    @IntegrationTest
    public void shouldCreate() {

        Restaurant restaurant = TestUtil.getRestaurantObject();

        given().baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(JSON)
                .request()
                .body(restaurant)
                .log().all()
                .when().post()
                .then()
                .log().all()
                .assertThat().statusCode(SC_CREATED);
    }

    @IntegrationTest
    public void shouldUpdate() {

        Restaurant restaurant = TestUtil.getRestaurantObject();

        given().baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(JSON)
                .request()
                .body(restaurant)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    public void shouldgetById() {

        Long id = 5L;

        Restaurant restaurant =
            given().baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .pathParam("id", id)
                .request()
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Restaurant.class);

        assertNotNull(restaurant);
    }
}
