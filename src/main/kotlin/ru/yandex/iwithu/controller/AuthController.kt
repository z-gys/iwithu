package ru.yandex.iwithu.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yandex.iwithu.dto.auth.LoginRequestDto
import ru.yandex.iwithu.dto.auth.LoginResponseDto
import ru.yandex.iwithu.dto.auth.RegistrationRequestDto
import ru.yandex.iwithu.service.AuthService
import javax.validation.Valid

@RestController
@Validated
@RequestMapping(
    produces = ["application/json"],
    consumes = ["application/json"]
)
class AuthController(
    private val authService: AuthService
) {
    @PostMapping(
        value = ["/register"]
    )
    fun register(
        @Valid @RequestBody registrationRequestDto: RegistrationRequestDto
    ): ResponseEntity<Unit> {
        authService.register(registrationRequestDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping(
        value = ["/login"]
    )
    fun login(
        @Valid @RequestBody loginRequestDto: LoginRequestDto
    ): ResponseEntity<LoginResponseDto> {
        return ResponseEntity.ok(authService.login(loginRequestDto))
    }
}
