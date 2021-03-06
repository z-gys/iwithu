package ru.yandex.iwithu.config

import at.favre.lib.crypto.bcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.index.Index
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import ru.yandex.iwithu.model.Token
import java.security.SecureRandom
import java.time.Duration

/**
@author ugoryntsev
 */
@Configuration
class AppConfig: WebMvcConfigurer {

    @Autowired
    private lateinit var userFromTokenArgumentResolver: UserFromTokenArgumentResolver

    @Autowired
    private lateinit var mongoOperations: MongoOperations

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userFromTokenArgumentResolver)
    }

    @Bean
    fun bcryptHasher(): BCrypt.Hasher = BCrypt.with(
        BCrypt.Version.VERSION_2A,
        SecureRandom(),
        LongPasswordStrategies.strict(BCrypt.Version.VERSION_2A)
    )

    @Bean
    fun bcryptVerifyer(): BCrypt.Verifyer = BCrypt.verifyer(
        BCrypt.Version.VERSION_2A,
        LongPasswordStrategies.strict(BCrypt.Version.VERSION_2A)
    )

    @EventListener(ContextRefreshedEvent::class)
    fun onContextRefresh(event: ContextRefreshedEvent): Unit {
        mongoOperations.indexOps(Token::class.java)
            .ensureIndex(
                Index().on("created", Sort.Direction.ASC)
                    .expire(Duration.ofDays(2L))
                    .background()
            )
    }
}