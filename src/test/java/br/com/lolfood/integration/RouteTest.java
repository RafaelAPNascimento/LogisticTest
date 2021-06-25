package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import io.restassured.http.ContentType;
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
    @DisplayName("Should get by ID")
    public void shouldGet() {

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .request()
                .log().all()
                .when().get()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }

    @IntegrationTest
    @DisplayName("Should update status to the next one")
    public void shouldUpdateStatus() {

        String routeId = "{\"id\": 3}";

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(routeId)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_OK);
    }
}
