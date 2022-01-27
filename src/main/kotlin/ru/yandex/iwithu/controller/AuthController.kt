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
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class AuthController {
    @PostMapping(
        value = ["/register"],
        consumes = ["application/json"]
    )
    fun register( @Valid @RequestBody registrationRequestDto: RegistrationRequestDto
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping(
        value = ["/login"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun login( @Valid @RequestBody loginRequestDto: LoginRequestDto
    ): ResponseEntity<LoginResponseDto> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
