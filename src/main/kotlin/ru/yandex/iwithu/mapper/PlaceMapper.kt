package ru.yandex.iwithu.mapper

import ru.yandex.iwithu.dto.events.PlaceDto
import ru.yandex.iwithu.model.Place

/**
@author ugoryntsev
 */

fun PlaceDto.toEntity(): Place = Place(
    lat, lon, address
)

fun Place.toDto(): PlaceDto = PlaceDto(
    lat, lon, address
)
