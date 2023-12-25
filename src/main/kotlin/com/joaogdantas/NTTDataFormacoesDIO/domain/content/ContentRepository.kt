package com.joaogdantas.NTTDataFormacoesDIO.domain.content

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

public interface ContentRepository : JpaRepository<EducationalContent, UUID> {
}