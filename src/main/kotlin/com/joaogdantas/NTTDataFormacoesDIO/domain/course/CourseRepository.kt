package com.joaogdantas.NTTDataFormacoesDIO.domain.course

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

public interface CourseRepository : JpaRepository<Course, Long> {
    fun findByTitle(title: String): Optional<Course>
}