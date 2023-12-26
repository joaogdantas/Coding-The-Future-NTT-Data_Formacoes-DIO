package com.joaogdantas.NTTDataFormacoesDIO.domain.course

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.dto.RegisterCourseDTO
import jakarta.persistence.*
import java.util.*

@Entity(name = "course")
@Table(name = "courses")
class Course(data: RegisterCourseDTO) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id_seq")
    @SequenceGenerator(name = "course_id_seq", sequenceName = "course_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "title")
    var title: String? = null;

    @Column(name = "partner")
    var partner: String? = null;

    @OneToMany(mappedBy = "course")
    var contents: List<EducationalContent>? = null

    init {
        this.title = data.title
        this.partner = data.partner
    }
}