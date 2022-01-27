package ru.yandex.iwithu.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Event (

    @Id
    val id: Long,
    val title: String,
    val category: String,
    val description: String,
    val time: LocalDateTime,
    val owner: String,
    val chatLink: String?,
    val capacity: Int?,
    val members: MutableSet<String> = mutableSetOf(),
    val place: Place?
) {
    companion object {
        const val SEQUENCE_ID = "event"
    }
}
