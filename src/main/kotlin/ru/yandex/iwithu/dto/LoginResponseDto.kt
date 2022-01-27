package ru.yandex.iwithu.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * Носитель токена
 * @param token токен
 */
data class LoginResponseDto(
    @get:NotNull  
    @field:JsonProperty("token") val token: String
) {

}

