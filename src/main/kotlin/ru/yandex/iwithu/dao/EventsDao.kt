package ru.yandex.iwithu.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.yandex.iwithu.model.Event

interface EventsDao: MongoRepository<Event, Long> {

    @Query("{\n" +
        "members: {\$elemMatch: {\$eq: ?0}}\n" +
        "}")
    fun findJoinedEvents( login: String, pageable: Pageable):Page<Event>

    @Query("{\n" +
        "owner: {\$eq: ?0}\n" +
        "}")
    fun findOwnedEvents(login: String, pageable: Pageable): Page<Event>
}
