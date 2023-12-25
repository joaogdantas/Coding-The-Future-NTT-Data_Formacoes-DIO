package com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Level
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import java.util.UUID

data class ReturnContentDTO(
    var id: UUID,
    var title: String,
    var durationInMinutes: Int,
    var technology: List<Technologies>,
    var level: Level
) {
    companion object {
        fun fromEntity(content: EducationalContent): ReturnContentDTO {
            return ReturnContentDTO(
                id = content.id!!,
                title = content.title!!,
                durationInMinutes = content.durationInMinutes!!,
                technology = content.technology!!,
                level = content.level!!
            )
        }
    }
}
