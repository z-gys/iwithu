package ru.yandex.iwithu.dto.events

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

/**
 * объект места проведения
 * @param lat Широта
 * @param lon Долгота
 * @param address Адрес
 */
data class PlaceDto(

    @get:NotNull  
    @field:JsonProperty("lat") val lat: Double,

    @get:NotNull  
    @field:JsonProperty("lon") val lon: Double,

    @field:JsonProperty("address") val address: String? = null
) {

}

