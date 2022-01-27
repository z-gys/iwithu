package ru.yandex.iwithu.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

/**
 * Тело запроса логина
 * @param login логин
 * @param password пароль
 */
data class LoginRequestDto(

    @get:NotNull  
    @field:JsonProperty("login") val login: String,

    @get:NotNull  
    @field:JsonProperty("password") val password: String
) {

}

