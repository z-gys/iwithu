package ru.yandex.iwithu.dao

import org.springframework.data.mongodb.repository.MongoRepository
import ru.yandex.iwithu.model.Event

interface EventsDao: MongoRepository<Event, Long> {

}
