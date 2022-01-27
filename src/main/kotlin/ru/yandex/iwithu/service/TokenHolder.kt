package ru.yandex.iwithu.service

import org.springframework.stereotype.Service
import ru.yandex.iwithu.model.User

@Service
class TokenHolder {
    companion object {
        private val ALLOWED_CHARS = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        private const val TOKEN_LEN = 48
    }
    private val tokenMap = HashMap<String, User>()

    fun createToken(user: User): String {
        val token = getRandomString(TOKEN_LEN)
        tokenMap[token] = user
        return token
    }

    fun getUserByToken(token: String) = tokenMap[token]

    fun getRandomString(length: Int): String {

        return (1..length).map { ALLOWED_CHARS.random() }
            .joinToString("")
    }
}
