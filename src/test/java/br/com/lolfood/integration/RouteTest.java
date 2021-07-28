package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Route;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RouteTest {

    private final String PATH = "/routes";

    @IntegrationTest
    @DisplayName("Should get suggested routes")
    public void shouldGet() {

        Route[] routes =
            given().baseUri(BASE_URI)
                .basePath(PATH)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Route[].class);
    }

    @IntegrationTest
    @DisplayName("Should confirm route")
    public void shouldUpdateStatus() {

        String routeId = "{\"id\": 3}";

        Route route =
            given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(routeId)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK)
                .extract().response().as(Route.class);
    }
}
