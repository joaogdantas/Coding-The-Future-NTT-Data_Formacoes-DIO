package com.joaogdantas.NTTDataFormacoesDIO.domain.user

import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.dto.RegisterUserDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity(name = "user")
@Table(name = "users")
class User {
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
    var favoriteTechnology: Technologies? = null;

    @Column(name = "reputation")
    var reputation: Int? = 0;

    constructor(data: RegisterUserDTO) {
        this.login = data.login
        this.email = data.email
        this.birthDate = data.birthDate
        this.name = data.name
        this.address = data.address
        this.favoriteTechnology = data.favoriteTechnology
    }
}