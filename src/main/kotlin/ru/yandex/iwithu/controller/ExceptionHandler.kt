package ru.yandex.iwithu.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.yandex.iwithu.exception.AlreadyExistsException
import ru.yandex.iwithu.exception.BadCredentialException
import ru.yandex.iwithu.exception.BadTokenException
import ru.yandex.iwithu.exception.CantJoinException
import ru.yandex.iwithu.exception.ForbiddenException
import ru.yandex.iwithu.exception.NotFoundException

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

    @ExceptionHandler(BadTokenException::class)
    fun onBadToken(): ResponseEntity<Unit> = ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .headers(errorHeaders("Bad token"))
        .build()

    @ExceptionHandler(NotFoundException::class)
    fun onNotFound(): ResponseEntity<Unit> = ResponseEntity
    .status(HttpStatus.NOT_FOUND)
    .headers(errorHeaders("Not found"))
    .build()

    @ExceptionHandler(ForbiddenException::class)
    fun onForbidden(): ResponseEntity<Unit> = ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .headers(errorHeaders("Forbidden"))
        .build()

    @ExceptionHandler(CantJoinException::class)
    fun onCantJoin(): ResponseEntity<Unit> = ResponseEntity
        .badRequest()
        .headers(errorHeaders("Can't join"))
        .build()

    private fun errorHeaders(value: String): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(ERROR_HEADER_NAME, value)
        return headers
    }
}