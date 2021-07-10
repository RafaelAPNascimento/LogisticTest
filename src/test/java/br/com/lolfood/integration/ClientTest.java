package br.com.lolfood.integration;

import br.com.lolfood.annotations.IntegrationTest;
import br.com.lolfood.model.Client;
import br.com.lolfood.util.TestUtil;
import org.junit.jupiter.api.*;

import static br.com.lolfood.integration.TestConfig.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ClientTest {

    private String PATH = "/client";

    @IntegrationTest
    @Order(1)
    public void shouldCreate() {

        Client client = TestUtil.getClient();

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(client)
                .log().all()
                .when().post()
                .then()
                .log().all()
                .assertThat().statusCode(SC_CREATED);
    }

    @IntegrationTest
    @Order(2)
    public void shouldUpdate() {

        Client client = TestUtil.getClient();

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(client)
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

        Client client =
                given().baseUri(BASE_URI)
                        .basePath(PATH + "/{id}")
                        .pathParam("id", id)
                        .request()
                        .log().all()
                        .when().get()
                        .then()
                        .log().all()
                        .assertThat().statusCode(SC_OK)
                        .extract().response().as(Client.class);

        assertNotNull(client);
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

        Client client = TestUtil.getClient();
        client.setLat(null);

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(client)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_BAD_REQUEST);
    }

    @IntegrationTest
    public void shouldNotAcceptInvalidId() {

        Client client = TestUtil.getClient();
        client.setId(-1L);

        given().baseUri(BASE_URI)
                .basePath(PATH)
                .contentType(JSON)
                .request()
                .body(client)
                .log().all()
                .when().put()
                .then()
                .log().all()
                .assertThat().statusCode(SC_BAD_REQUEST);
    }
}
