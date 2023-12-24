package com.joaogdantas.NTTDataFormacoesDIO.domain.user.dto

import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import java.time.LocalDate

data class RegisterUserDTO(
    var login: String,

    var email: String,

    var birthDate: LocalDate,

    var name: String,

    var address: String,

    var favoriteTechnology: Technologies
)
