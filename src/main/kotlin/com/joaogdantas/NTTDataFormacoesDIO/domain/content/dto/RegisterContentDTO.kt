package com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto

import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Level
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

data class RegisterContentDTO(
    var title: String,

    var durationInMinutes: Int,

    var technology: List<Technologies>,

    var level: Level
)
