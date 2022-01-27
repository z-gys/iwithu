package ru.yandex.iwithu.mapper

import ru.yandex.iwithu.dto.auth.RegistrationRequestDto
import ru.yandex.iwithu.model.User

/**
@author ugoryntsev
 */

fun RegistrationRequestDto.toUser() : User = User(
    this.login,
    this.password,
    this.name,
    this.about
)