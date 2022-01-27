package ru.yandex.iwithu.dao

import org.springframework.data.mongodb.repository.MongoRepository
import ru.yandex.iwithu.model.Token

/**
@author ugoryntsev
 */
interface TokenDao: MongoRepository<Token, String> {
}