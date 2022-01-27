package ru.yandex.iwithu.dto

import com.fasterxml.jackson.annotation.JsonProperty
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
    @field:JsonProperty("time") val time: String,

    @field:JsonProperty("id") val id: Int? = null,

    @field:JsonProperty("capacity") val capacity: Int? = null
) {

}

