package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Order;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest {

    private final String PATH = "/order";

    @IntegrationTest
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
    public void shouldUpdate() {

        String id = "{\"id\": 2}";

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(id)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    public void shouldFindByRestaurantId() {

        Long restaurantId = 5L;

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .queryParam("restaurantId", restaurantId)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    @DisplayName("Should get by ID")
    public void shouldgetById() {

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
