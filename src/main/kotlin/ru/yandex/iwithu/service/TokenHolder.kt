package ru.yandex.iwithu.service

import org.springframework.stereotype.Service
import ru.yandex.iwithu.dao.TokenDao
import ru.yandex.iwithu.dao.UserDao
import ru.yandex.iwithu.model.Token
import ru.yandex.iwithu.model.User
import java.time.LocalDateTime

@Service
class TokenHolder(
    private val tokenDao: TokenDao,
    private val userDao: UserDao
) {
    companion object {
        private val ALLOWED_CHARS = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        private const val TOKEN_LEN = 48
    }

    fun createToken(user: User): String {
        val token = getRandomString()
        tokenDao.save(Token(token, user.login, LocalDateTime.now()))
        return token
    }

    fun getUserByToken(token: String): User? {
        return tokenDao.findById(token).orElse(null)?.let { userDao.findById(it.login).orElse(null) }
    }

    private fun getRandomString(): String {

        return (1..TOKEN_LEN).map { ALLOWED_CHARS.random() }
            .joinToString("")
    }
}
