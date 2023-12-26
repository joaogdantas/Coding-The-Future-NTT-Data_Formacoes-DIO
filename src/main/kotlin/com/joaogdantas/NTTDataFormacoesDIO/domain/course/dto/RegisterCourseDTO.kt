package com.joaogdantas.NTTDataFormacoesDIO.domain.course.dto

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import jakarta.persistence.Column
import jakarta.persistence.OneToMany

data class RegisterCourseDTO(
    var title: String,

    var partner: String
)
