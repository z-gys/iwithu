package ru.yandex.iwithu.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ru.yandex.iwithu.dao.EventsDao
import ru.yandex.iwithu.dao.SequenceDao
import ru.yandex.iwithu.dto.events.EventCreateDto
import ru.yandex.iwithu.dto.events.EventDto
import ru.yandex.iwithu.dto.events.ShortEventDto
import ru.yandex.iwithu.exception.ForbiddenException
import ru.yandex.iwithu.exception.NotFoundException
import ru.yandex.iwithu.mapper.toEvent
import ru.yandex.iwithu.mapper.toFullDto
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

    fun getJoinedEvents(pageable: Pageable, user: User): Page<ShortEventDto> {
        val page = eventsDao.findJoinedEvents(user.login, pageable)
        return page.map { it.toShortDto() }
    }

    fun getEvent(eventId: Long): EventDto =
        eventsDao.findById(eventId).map { it.toFullDto() }.orElse(null) ?: throw NotFoundException()

    fun deleteEvent(eventId: Long, user: User) {
        val eventO = eventsDao.findById(eventId)
        if (eventO.isEmpty) {
            return
        }
        val event = eventO.get()
        if (event.owner == user.login) {
            eventsDao.deleteById(eventId)
        } else {
            throw ForbiddenException()
        }
    }

    fun joinToEvent(eventId: Long, user: User) {
        val event = findEvent(eventId)
        event.members.add(user.login)
        eventsDao.save(event)
    }

    fun leaveEvent(eventId: Long, user: User) {
        val event = findEvent(eventId)
        event.members.remove(user.login)
        eventsDao.save(event)
    }

    fun editEvent(eventId: Long, eventDto: EventCreateDto, user: User) {
        val event = findEvent(eventId)
        if (event.owner != user.login) {
            throw ForbiddenException()
        }

        val newEvent = eventDto.toEvent(eventId, user.login)
        eventsDao.save(newEvent)
    }

    fun getOwnedEvents(pageable: Pageable, user: User): Page<ShortEventDto>? {
        val page = eventsDao.findOwnedEvents(user.login, pageable)
        return page.map { it.toShortDto() }
    }

    private fun findEvent(eventId: Long): Event {
        return eventsDao.findById(eventId).orElse(null) ?: throw NotFoundException()
    }
}
