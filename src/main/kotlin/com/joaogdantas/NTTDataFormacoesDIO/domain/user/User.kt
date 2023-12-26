package com.joaogdantas.NTTDataFormacoesDIO.domain.user

import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.dto.RegisterUserDTO
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity(name = "user")
@Table(name = "users")
class User(data: RegisterUserDTO) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    val id: UUID? = null

    @Column(name = "login")
    var login: String? = null;

    @Column(name = "email")
    var email: String? = null;

    @Column(name = "birth_date")
    var birthDate: LocalDate? = null;

    @Column(name = "name")
    var name: String? = null;

    @Column(name = "address")
    var address: String? = null;

    @Column(name = "technology")
    @Enumerated(EnumType.STRING)
    var favoriteTechnology: Technologies? = null;

    @Column(name = "reputation")
    var reputation: Int? = 0;

    init {
        this.login = data.login
        this.email = data.email
        this.birthDate = data.birthDate
        this.name = data.name
        this.address = data.address
        this.favoriteTechnology = data.favoriteTechnology
        this.reputation = 0
    }
}