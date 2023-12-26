package com.joaogdantas.NTTDataFormacoesDIO.domain.content

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

public interface ContentRepository : JpaRepository<EducationalContent, Long> {
    fun findByTitle(title: String): Optional<EducationalContent>
}