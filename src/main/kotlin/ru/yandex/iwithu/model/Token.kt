package ru.yandex.iwithu.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
@author ugoryntsev
 */
@Document
class Token(
    @Id
    val token: String,
    val login: String,
    val created: LocalDateTime
)