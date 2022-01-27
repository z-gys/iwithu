package ru.yandex.iwithu.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
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
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class EventsController(
    private val eventsService: EventsService
) {

    @PostMapping
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
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("my")
    fun eventsMyGet(
        pageable: Pageable,
        @AuthenticatedUser user: User
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping("{eventId}")
    fun eventsEventIdGet(
        @PathVariable("eventId") eventId: Int,
        @AuthenticatedUser user: User
    ): ResponseEntity<EventDto> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping( "{eventId}")
    fun eventsEventIdDelete(
        @PathVariable("eventId") eventId: Int,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping("{eventId}/join")
    fun eventsEventIdJoinPost(
        @PathVariable("eventId") eventId: Int,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping("{eventId}/join")
    fun eventsEventIdJoinDelete(
        @PathVariable("eventId") eventId: Int,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping("{eventId}")
    fun eventsEventIdPut(
        @PathVariable("eventId") eventId: Int,
        @Valid @RequestBody eventDto: EventDto,
        @AuthenticatedUser user: User
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
