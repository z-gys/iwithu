package ru.yandex.iwithu.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yandex.iwithu.config.AuthenticatedUser
import ru.yandex.iwithu.dto.events.EventCreateDto
import ru.yandex.iwithu.dto.events.EventDto
import ru.yandex.iwithu.dto.events.ShortEventDto
import ru.yandex.iwithu.model.User
import ru.yandex.iwithu.service.EventsService
import javax.validation.Valid

@RestController
@Validated
@RequestMapping(
    "events",
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class EventsController(
    private val eventsService: EventsService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsPost(
        @Valid @RequestBody eventDto: EventCreateDto,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        eventsService.createEvent(eventDto, user)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun eventsGet(
        pageRequest: Pageable,
        @AuthenticatedUser user: User
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity.ok(eventsService.getEvents(pageRequest))
    }

    @GetMapping("joined")
    fun eventsJoinedGet(
        pageable: Pageable,
        @AuthenticatedUser user: User
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity.ok(eventsService.getJoinedEvents(pageable, user))
    }

    @GetMapping("my")
    fun eventsMyGet(
        pageable: Pageable,
        @AuthenticatedUser user: User
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity.ok(eventsService.getOwnedEvents(pageable, user))
    }

    @GetMapping("{eventId}")
    fun eventsEventIdGet(
        @PathVariable("eventId") eventId: Long,
        @AuthenticatedUser user: User
    ): ResponseEntity<EventDto> {
        return ResponseEntity.ok(eventsService.getEvent(eventId, user))
    }

    @DeleteMapping( "{eventId}")
    fun eventsEventIdDelete(
        @PathVariable("eventId") eventId: Long,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        eventsService.deleteEvent(eventId, user)
        return ResponseEntity.ok().build()
    }

    @PostMapping("{eventId}/join", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsEventIdJoinPost(
        @PathVariable("eventId") eventId: Long,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        eventsService.joinToEvent(eventId, user)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("{eventId}/join")
    fun eventsEventIdJoinDelete(
        @PathVariable("eventId") eventId: Long,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        eventsService.leaveEvent(eventId, user)
        return ResponseEntity.ok().build()
    }

    @PutMapping("{eventId}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun eventsEventIdPut(
        @PathVariable("eventId") eventId: Long,
        @Valid @RequestBody eventDto: EventCreateDto,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        eventsService.editEvent(eventId, eventDto, user)
        return ResponseEntity.ok().build()
    }
}
