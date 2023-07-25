package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests extends TestBase {

    @Test
    void createUserTest() {
        String body = "{ \"name\": \"Alex\", \"job\": \"developer\" }";

            given()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Alex"))
                .body("job", is("developer"));
    }

    @Test
    void updateUserTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"qa\" }";

            given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("qa"));
    }

    @Test
    void deleteUserTest() {
        given()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void getYearOfSingleResourceTest() {
        given()
                .log().uri()
                .when()
                .get("/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.year", is(2001));
    }

    @Test
    void checkPagesWithUsersTest() {
        given()
                .log().uri()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2));
    }
}
