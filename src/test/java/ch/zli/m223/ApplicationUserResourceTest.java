package ch.zli.m223;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.h2.H2DatabaseTestResource;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "test@example.com", roles = "User")
public class ApplicationUserResourceTest {

  @Test
  @Order(1)
  public void testCreateEndpoint() {
    given()
      .when().get("/registration")
      .then()
       .statusCode(201);

    given()
      .when().get("/registration")
      .then()
       .statusCode(200)
       .body("size()", is(4));
  }

  @Test
  @Order(2)
  public void testChangePasswordEndpoint() {
    given()
      .when().patch("/registration/changePassword/" + 1)
      .then()
        .statusCode(204);
  }
}