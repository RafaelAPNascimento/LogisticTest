package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.*;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RestaurantTest {

    private String PATH = "/restaurant";

    @IntegrationTest
    @Order(1)
    public void shouldCreate() {

        Restaurant restaurant = TestUtil.getRestaurant();

        given().baseUri(BASE_URI)
                .basePath(PATH)
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
    @Order(2)
    public void shouldUpdate() {

        Restaurant restaurant = TestUtil.getRestaurant();
        given().baseUri(BASE_URI)
                .basePath(PATH)
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
    @DisplayName("Should get by ID")
    @Order(3)
    public void shouldgetById() {

        Long id = 5L;

        Restaurant restaurant =
            given().baseUri(BASE_URI)
                .basePath(PATH + "/{id}")
                .pathParam("id", id)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Restaurant.class);

        assertNotNull(restaurant);
    }

    @IntegrationTest
    @DisplayName("Should NOT get by ID")
    public void shouldNotGetById() {

        Long id = 9999L;

        given().baseUri(BASE_URI)
                .basePath(PATH + "/{id}")
                .pathParam("id", id)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_NO_CONTENT);
    }

    @IntegrationTest
    public void shouldNotAcceptInvalidPayload() {

        Restaurant restaurant = TestUtil.getRestaurant();
        restaurant.setLat(null);

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(restaurant)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_BAD_REQUEST);
    }

    @IntegrationTest
    public void shouldNotAcceptInvalidId() {

        Restaurant restaurant = TestUtil.getRestaurant();
        restaurant.setId(-1L);

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(restaurant)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_BAD_REQUEST);
    }
}
