package ru.yandex.iwithu.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
@author ugoryntsev
 */
@Document(collection = "sequence")
class IdSequence(
    @Id
    val id: String,
    val seq: Long
)