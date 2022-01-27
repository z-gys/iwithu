package ru.yandex.iwithu.dto.events

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

/**
 * Объект эвента для отображения в списке
 * @param category Категория
 * @param title Название события
 * @param description Описание события
 * @param membersCount Количество присоединившихся
 * @param time Дата и время в формате \"yyyy-MM-dd'T'HH:mm:ss'Z'\"
 * @param id идентификатор события
 * @param capacity Вместимость в человеках. Если не передано - неограниченная
 */
data class ShortEventDto(

    @get:NotNull  
    @field:JsonProperty("category") val category: String,

    @get:NotNull  
    @field:JsonProperty("title") val title: String,

    @get:NotNull  
    @field:JsonProperty("description") val description: String,

    @get:NotNull  
    @field:JsonProperty("membersCount") val membersCount: Int,

    @get:NotNull
    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty("time") val time: LocalDateTime,

    @field:JsonProperty("id") val id: Long,

    @field:JsonProperty("capacity") val capacity: Int? = null
) {

}

