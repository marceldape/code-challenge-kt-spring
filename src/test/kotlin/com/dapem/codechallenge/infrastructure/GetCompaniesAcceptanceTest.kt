package com.dapem.codechallenge.infrastructure

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetCompaniesAcceptanceTest {

    @LocalServerPort
    var springBootPort = 0

    @Test
    fun `given a correct symbol and interval, when search then return a Stock`() {
        RestAssured
            .given()
            .`when`()
            .port(springBootPort)
            .get("/api/v1/companies")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body(notNullValue())
    }


}