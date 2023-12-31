package com.joaogdantas.NTTDataFormacoesDIO.domain.user

import com.joaogdantas.NTTDataFormacoesDIO.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

public interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
    fun findByLogin(login: String): Optional<User>

}