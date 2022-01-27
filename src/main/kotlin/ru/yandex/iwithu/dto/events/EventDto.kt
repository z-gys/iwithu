package ru.yandex.iwithu.dto.events

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * Тело запроса создания события
 * @param title Название события
 * @param category Категория события
 * @param description Описание события
 * @param time Дата и время начала события в формате \"yyyy-MM-dd'T'HH:mm:ss'Z'\"
 * @param owner Логин создателя события
 * @param chatLink Ссылка на чат
 * @param capacity вместимость события
 * @param place 
 */
data class EventDto(

    @get:NotNull
    @field:JsonProperty("title") val title: String,

    @get:NotNull
    @field:JsonProperty("category") val category: String,

    @get:NotNull
    @field:JsonProperty("description") val description: String,

    @get:NotNull
    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @field:JsonProperty("time") val time: Instant,

    @field:JsonProperty("owner") val owner: String,

    @field:JsonProperty("chatLink") val chatLink: String? = null,

    @field:JsonProperty("capacity") val capacity: Int? = null,

    @field:Valid
    @field:JsonProperty("place") val place: PlaceDto? = null
) {

}

