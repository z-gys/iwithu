package ru.yandex.iwithu.service

import at.favre.lib.crypto.bcrypt.BCrypt
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.yandex.iwithu.dao.UserDao
import ru.yandex.iwithu.dto.auth.LoginRequestDto
import ru.yandex.iwithu.dto.auth.LoginResponseDto
import ru.yandex.iwithu.dto.auth.RegistrationRequestDto
import ru.yandex.iwithu.exception.AlreadyExistsException
import ru.yandex.iwithu.exception.BadCredentialException
import ru.yandex.iwithu.mapper.toUser
import kotlin.math.log

/**
@author ugoryntsev
 */
@Service
class AuthService(
    private val userDao: UserDao,
    private val bcryptHasher: BCrypt.Hasher,
    private val bcryptVerifyer: BCrypt.Verifyer,
    private val tokenHolder: TokenHolder
) {

    companion object {
        private const val COST = 11
    }

    fun register(registrationRequestDto: RegistrationRequestDto) {

        if (userDao.existsById(registrationRequestDto.login)) {
            throw AlreadyExistsException()
        }

        registrationRequestDto.password = bcryptHasher.hashToString(COST, registrationRequestDto.password.toCharArray())
        val user = registrationRequestDto.toUser()
        userDao.save(user)
    }

    fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        val userO = userDao.findById(loginRequestDto.login)
        if (userO.isEmpty) {
            throw BadCredentialException()
        }

        val user = userO.get()
        if (!bcryptVerifyer.verify(loginRequestDto.password.toCharArray(), user.password).verified) {
            throw BadCredentialException()
        }

        return LoginResponseDto(tokenHolder.createToken(user))
    }
}