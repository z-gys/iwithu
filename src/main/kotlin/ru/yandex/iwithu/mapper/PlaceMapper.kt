package ru.yandex.iwithu.mapper

import ru.yandex.iwithu.dto.events.PlaceDto
import ru.yandex.iwithu.model.Place

/**
@author ugoryntsev
 */

fun PlaceDto.toPlace(): Place = Place(
    lat, lon, address
)
