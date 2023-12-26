package com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.Course
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Level
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import java.util.UUID

data class ReturnContentDTO(
    var id: Long,
    var title: String,
    var durationInMinutes: Int,
    var technology: List<Technologies>,
    var level: Level,
    var course: Course
) {
    companion object {
        fun fromEntity(content: EducationalContent): ReturnContentDTO {
            return ReturnContentDTO(
                id = content.id!!,
                title = content.title!!,
                durationInMinutes = content.durationInMinutes!!,
                technology = content.technology!!,
                level = content.level!!,
                course = content.course!!
            )
        }
    }
}
