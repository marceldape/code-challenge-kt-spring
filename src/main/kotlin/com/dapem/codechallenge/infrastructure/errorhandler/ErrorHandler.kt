package com.dapem.codechallenge.infrastructure.errorhandler

import com.dapem.codechallenge.domain.exceptions.CompanyNotFound
import com.dapem.codechallenge.domain.exceptions.InvalidIntervalException
import com.dapem.codechallenge.domain.exceptions.InvalidStockSymbolException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler
    fun handleUserNotFoundException(e: CompanyNotFound): ResponseEntity<ErrorResponse> {
        return buildResponse(message = e.message!!, status = HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleInvalidStockSymbol(e: InvalidStockSymbolException): ResponseEntity<ErrorResponse> {
        return buildResponse(message = e.message!!, status = HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleInvalidInterval(e: InvalidIntervalException): ResponseEntity<ErrorResponse> {
        return buildResponse(message = e.message!!, status = HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        return buildResponse(message = e.message!!, status = HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleException(exception: HttpMessageConversionException): ResponseEntity<ErrorResponse> {
        return buildResponse(exception.message!!, HttpStatus.BAD_REQUEST)
    }


    private fun buildResponse(message: String, status: HttpStatus): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(message, status.value(), status.reasonPhrase), status)
    }
}
