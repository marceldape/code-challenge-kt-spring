package com.dapem.codechallenge.infrastructure

import io.restassured.RestAssured
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchStocksAcceptanceTest {

    @LocalServerPort
    var springBootPort = 0

    @Test
    fun `given a correct symbol and interval, when search then return a Stock`() {
        getSearchStock(symbol = "AAPL", interval = "weekly").then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("symbol", equalTo("AAPL"))
            .body("currency", equalTo("USD"))
            .body("interval", equalTo("weekly"))
            .body("value", notNullValue())
    }

    @Test
    fun `given an invalid symbol, when search then an exception is thrown`() =
        getSearchStockExpectErrorStatusCode(symbol = "ABCD", interval = "weekly", status = HttpStatus.NOT_FOUND)


    @Test
    fun `given an invalid symbol by regex, when search then an exception is thrown`() =
        getSearchStockExpectErrorStatusCode(symbol = "12ab", interval = "weekly", status = HttpStatus.BAD_REQUEST)

    @Test
    fun `given an interval, when search then an exception is thrown`() =
        getSearchStockExpectErrorStatusCode(symbol = "12ab", interval = "hourly", status = HttpStatus.BAD_REQUEST)

    private fun getSearchStock(symbol: String, interval: String): Response {
        return RestAssured
            .given()
            .`when`()
            .port(springBootPort)
            .get("/api/v1/stocks/".plus(symbol).plus("?interval=$interval"))
    }

    private fun getSearchStockExpectErrorStatusCode(symbol: String, interval: String, status: HttpStatus) {
        getSearchStock(symbol = symbol, interval = interval).then()
            .assertThat()
            .statusCode(status.value())
    }

}