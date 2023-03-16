package com.github.antoniomacri.infinispan.test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BooksResourceTest {

    @Test
    public void testGetPutDelete() {
        given()
                .when().get("/books/0-679-77543-9")
                .then()
                .statusCode(404);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "title": "The Wind-Up Bird Chronicle",
                          "publicationYear": 1997,
                          "authors": [
                            {
                              "name": "Haruki",
                              "surname": "Murakami"
                            }
                          ]
                        }""")
                .put("/books/0-679-77543-9")
                .then()
                .statusCode(200);

        given()
                .when().get("/books/0-679-77543-9")
                .then()
                .statusCode(200)
                .body("title", is("The Wind-Up Bird Chronicle"))
                .body("publicationYear", is(1997))
                .body("authors[0].name", is("Haruki"))
                .body("authors[0].surname", is("Murakami"));

        given()
                .when().delete("/books/0-679-77543-9")
//                .then()
//                .statusCode(200)
//                .body("title", is("The Wind-Up Bird Chronicle"))
//                .body("publicationYear", is(1997))
//                .body("authors[0].name", is("Haruki"))
//                .body("authors[0].surname", is("Murakami"))
        ;

        given()
                .when().get("/books/0-679-77543-9")
                .then()
                .statusCode(404);
    }
}
