package com.renanwillian.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
class ProducerEndpointTest {

    @Test
    void getProducerAwardInterval() {
        given().when().get("/producer/award-interval")
               .then()
               .statusCode(200)
               .body("min[0].producer", is("Joel Silver"))
               .body("min[0].interval", is(1))
               .body("min[0].previousWin", is(1990))
               .body("min[0].followingWin", is(1991))
               .body("max[0].producer", is("Matthew Vaughn"))
               .body("max[0].interval", is(13))
               .body("max[0].previousWin", is(2002))
               .body("max[0].followingWin", is(2015));
    }
}