package ru.yandex.iwithu.controller

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.yandex.iwithu.dto.EventDto
import ru.yandex.iwithu.dto.ShortEventDto
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class EventsController() {

    @DeleteMapping(
        value = ["/events/{eventId}"]
    )
    fun eventsEventIdDelete(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @PathVariable("eventId") eventId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping(
        value = ["/events/{eventId}"],
        produces = ["application/json"]
    )
    fun eventsEventIdGet(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @PathVariable("eventId") eventId: Int
    ): ResponseEntity<EventDto> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @DeleteMapping(
        value = ["/events/{eventId}/join"]
    )
    fun eventsEventIdJoinDelete(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @PathVariable("eventId") eventId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping(
        value = ["/events/{eventId}/join"]
    )
    fun eventsEventIdJoinPost(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @PathVariable("eventId") eventId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PutMapping(
        value = ["/events/{eventId}"],
        consumes = ["application/json"]
    )
    fun eventsEventIdPut(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @PathVariable("eventId") eventId: Int,
        @Valid @RequestBody eventDto: EventDto
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping(
        value = ["/events"],
        produces = ["application/json"]
    )
    fun eventsGet(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @RequestParam(value = "page", required = false) page: Int?,
        @RequestParam(value = "size", required = false) size: Int?
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping(
        value = ["/events/joined"],
        produces = ["application/json"]
    )
    fun eventsJoinedGet(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @RequestParam(value = "page", required = false) page: Int?,
        @RequestParam(value = "size", required = false) size: Int?
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @GetMapping(
        value = ["/events/my"],
        produces = ["application/json"]
    )
    fun eventsMyGet(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @RequestParam(value = "page", required = false) page: Int?,
        @RequestParam(value = "size", required = false) size: Int?
    ): ResponseEntity<Page<ShortEventDto>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @PostMapping(
        value = ["/events"],
        consumes = ["application/json"]
    )
    fun eventsPost(
        @RequestHeader(value = "Authorization", required = true) authorization: String,
        @Valid @RequestBody(required = false) eventDto: EventDto?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
