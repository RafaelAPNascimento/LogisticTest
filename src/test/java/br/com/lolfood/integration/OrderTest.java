package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Order;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.*;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static br.com.lolfood.model.OrderStatus.RECEIVING;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

    private final String PATH = "/order";

    @AfterAll
    public void sanitize() {
        given().baseUri(BASE_URI)
                .basePath("/sanitize")
                .post()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    @org.junit.jupiter.api.Order(1)
    public void shouldCreate() {

        Order order = TestUtil.getOrder();

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(order)
                .log().all()
                .when().post()
                .then()
                .log().all()
                .assertThat().statusCode(SC_CREATED);
    }

    @IntegrationTest
    @org.junit.jupiter.api.Order(2)
    public void shouldUpdate() {

        String id = "{\"id\": 2}";

        Order order =
            given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(id)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Order.class);

        Assertions.assertEquals(RECEIVING, order.getStatus());
    }

    @IntegrationTest
    @org.junit.jupiter.api.Order(3)
    public void shouldFindByRestaurantId() {

        Long restaurantId = 3L;

        Order[] ordersByRestaurant =
            given().baseUri(BASE_URI)
                .basePath(PATH)
                .queryParam("restaurantId", restaurantId)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Order[].class);


    }

    @IntegrationTest
    @DisplayName("Should get by ID")
    @org.junit.jupiter.api.Order(4)
    public void shouldGetById() {

        Long id = 2L;

        given().baseUri(BASE_URI)
                .basePath(PATH + "/{id}")
                .pathParam("id", id)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }
}
