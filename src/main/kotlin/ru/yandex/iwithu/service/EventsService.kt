package ru.yandex.iwithu.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.yandex.iwithu.dao.EventsDao
import ru.yandex.iwithu.dao.SequenceDao
import ru.yandex.iwithu.dto.events.EventCreateDto
import ru.yandex.iwithu.dto.events.ShortEventDto
import ru.yandex.iwithu.mapper.toEvent
import ru.yandex.iwithu.mapper.toShortDto
import ru.yandex.iwithu.model.Event
import ru.yandex.iwithu.model.User

@Service
class EventsService(
    private val eventsDao: EventsDao,
    private val sequenceDao: SequenceDao
) {
    fun createEvent(eventCreateDto: EventCreateDto, user: User) {
        val id = sequenceDao.getNextSequenceId(Event.SEQUENCE_ID)
        val owner = user.login

        val event = eventCreateDto.toEvent(id, owner)
        eventsDao.save(event)
    }

    fun getEvents(pageRequest: Pageable): Page<ShortEventDto> {
        val page = eventsDao.findAll(pageRequest)
        return page.map { it.toShortDto() }
    }
}
