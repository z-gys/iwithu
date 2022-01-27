package ru.yandex.iwithu.config

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import ru.yandex.iwithu.exception.BadTokenException
import ru.yandex.iwithu.service.TokenHolder

/**
@author ugoryntsev
 */
@Component
class UserFromTokenArgumentResolver(
    private val tokenHolder: TokenHolder
): HandlerMethodArgumentResolver {

    companion object {
        private const val TOKEN_HEADER = "Authorization"
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.getParameterAnnotation(AuthenticatedUser::class.java) != null

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val token = webRequest.getHeader(TOKEN_HEADER) ?: throw BadTokenException()
        return tokenHolder.getUserByToken(token) ?: throw BadTokenException()
    }
}