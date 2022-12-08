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
@TestSecurity(user = "test@example.com", roles = "Admin")
public class AdminBookingResourceTest {

  @Test
  @Order(1)
  public void testIndexEndpoint() {
    given()
      .when().get("/bookings")
      .then()
       .statusCode(200)
       .body("size()", is(3));
  }

  @Test
  @Order(2)
  public void testReadEndpoint() {
    given()
      .when().get("/bookings/" + 1)
      .then()
       .statusCode(200);
  }

  @Test
  @Order(3)
  public void testDeleteEndpoint() {
    given()
      .when().delete("/bookings/" + 1)
      .then()
        .statusCode(204);
    

    given()
      .when().get("/bookings")
      .then()
        .statusCode(200)
        .body("size()", is(2));
  }

  @Test
  @Order(4)
  public void testUpdateEndpoint() {
    given()
      .when().put("/bookings"  + 1)
      .then()
        .statusCode(204);
  }

  @Test
  @Order(5)
  public void testAcceptEndpoint() {
    given()
      .when().put("/bookings"  + 1)
      .then()
        .statusCode(204);
  }
}