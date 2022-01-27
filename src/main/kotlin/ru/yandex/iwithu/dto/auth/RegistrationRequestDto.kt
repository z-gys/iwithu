package ru.yandex.iwithu.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

/**
 * Тело запроса регистрации
 * @param name Имя
 * @param login уникальный логин
 * @param password пароль
 * @param about О себе
 */
data class RegistrationRequestDto(

    @get:NotNull  
    @field:JsonProperty("name") val name: String,

    @get:NotNull  
    @field:JsonProperty("login") val login: String,

    @get:NotNull  
    @field:JsonProperty("password") var password: String,

    @field:JsonProperty("about") val about: String? = null
) {

}

