package ru.yandex.iwithu.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
@author ugoryntsev
 */
@Document
class User(

    @Id
    val login: String,

    var password: String,

    var name: String,

    var about: String?
)
