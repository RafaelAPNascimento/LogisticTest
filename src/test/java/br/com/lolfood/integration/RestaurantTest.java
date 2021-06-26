package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Restaurant;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestaurantTest {

    private String PATH = "/restaurant";

    @IntegrationTest
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
