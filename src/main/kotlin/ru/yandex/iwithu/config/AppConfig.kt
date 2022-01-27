package ru.yandex.iwithu.config

import at.favre.lib.crypto.bcrypt.BCrypt
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.SecureRandom

/**
@author ugoryntsev
 */
@Configuration
class AppConfig {

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
}