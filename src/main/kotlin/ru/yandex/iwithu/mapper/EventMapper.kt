package ru.yandex.iwithu.mapper

import ru.yandex.iwithu.dto.events.EventCreateDto
import ru.yandex.iwithu.dto.events.EventDto
import ru.yandex.iwithu.dto.events.ShortEventDto
import ru.yandex.iwithu.model.Event

/**
@author ugoryntsev
 */

fun EventCreateDto.toEvent(id: Long, owner: String): Event = Event(
    id,
    title,
    category,
    description,
    time,
    owner,
    chatLink,
    capacity,
    mutableSetOf(),
    place?.toEntity()
)

fun Event.toShortDto(): ShortEventDto = ShortEventDto(
    category,
    title,
    description,
    members.size,
    time,
    id,
    capacity,
    chatLink
)

fun Event.toFullDto(canJoin: Boolean): EventDto = EventDto(
    title,
    category,
    description,
    time,
    owner,
    members,
    canJoin,
    chatLink,
    capacity,
    place?.toDto()
)
