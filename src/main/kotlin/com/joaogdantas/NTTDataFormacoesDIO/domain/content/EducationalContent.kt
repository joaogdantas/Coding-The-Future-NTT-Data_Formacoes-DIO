package com.joaogdantas.NTTDataFormacoesDIO.domain.content

import com.joaogdantas.NTTDataFormacoesDIO.domain.course.Course
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Level
import com.joaogdantas.NTTDataFormacoesDIO.domain.enums.Technologies
import com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto.RegisterContentDTO
import com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto.ReturnContentDTO
import jakarta.persistence.*
import org.hibernate.Hibernate.map
import java.util.*

@Entity(name = "content")
@Table(name = "contents")
class EducationalContent(data: RegisterContentDTO) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_id_seq")
    @SequenceGenerator(name = "content_id_seq", sequenceName = "content_id_seq", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null

    @Column(name = "title")
    var title: String? = null;

    @Column(name = "duration")
    var durationInMinutes: Int? = null;

    @Column(name = "technology")
    @Enumerated(EnumType.STRING)
    var technology: List<Technologies>? = null;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    var level: Level? = null;

    @ManyToOne
    @JoinColumn(name = "course_id")
    var course: Course? = null

    init {
        this.title = data.title
        this.durationInMinutes = data.durationInMinutes
        this.technology = data.technology
        this.level = data.level
    }
}