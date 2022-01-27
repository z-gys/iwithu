package ru.yandex.iwithu.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.yandex.iwithu.exception.AlreadyExistsException
import ru.yandex.iwithu.exception.BadCredentialException

/**
@author ugoryntsev
 */
@ControllerAdvice
class ExceptionHandler {

    companion object {
        private const val ERROR_HEADER_NAME = "X-Error"
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun onAlreadyExists(): ResponseEntity<Unit> = ResponseEntity
        .badRequest()
        .headers(errorHeaders("Already exists"))
        .build()

    @ExceptionHandler(BadCredentialException::class)
    fun onBadCredentials(): ResponseEntity<Unit> = ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .headers(errorHeaders("Bad credentials"))
        .build()

    private fun errorHeaders(value: String): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(ERROR_HEADER_NAME, value)
        return headers
    }
}