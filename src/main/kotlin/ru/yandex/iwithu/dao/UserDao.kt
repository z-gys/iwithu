package ru.yandex.iwithu.dao

import org.springframework.data.mongodb.repository.MongoRepository
import ru.yandex.iwithu.model.User

/**
@author ugoryntsev
 */
interface UserDao: MongoRepository<User, String>{

}