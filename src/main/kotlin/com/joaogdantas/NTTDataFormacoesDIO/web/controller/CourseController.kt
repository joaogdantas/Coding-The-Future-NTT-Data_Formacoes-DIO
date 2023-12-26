package com.joaogdantas.NTTDataFormacoesDIO.web.controller

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.Course
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.CourseRepository
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.dto.RegisterCourseDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/courses")
class CourseController {
    private lateinit var course: Course

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @PostMapping("/create")
    fun registerCourse(@RequestBody data: RegisterCourseDTO,
                        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<String> {

        val existentTitle: Optional<Course> = courseRepository.findByTitle(data.title)

        if(existentTitle.isPresent){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body("Já existe um curso com esse título, por favor escolha outro")
        }

        val newCourse = Course(data)
        courseRepository.save(newCourse)

        val uri = uriComponentsBuilder
            .path("/contents/{id}")
            .buildAndExpand(newCourse.id)
            .toUri()

        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Curso registrado com sucesso")
    }

    @GetMapping("/all")
    fun getAllCourses(): ResponseEntity<List<Course>> {
        val courses = courseRepository.findAll()
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<Course> {
        val optionalCourse = courseRepository.findById(id)

        return if (optionalCourse.isPresent) {
            return ResponseEntity.ok(optionalCourse.get())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/{id}")
    fun deleteCourse(@PathVariable id: Long): ResponseEntity<String> {
        val optionalCourse = courseRepository.findById(id)

        return if (optionalCourse.isPresent) {
            courseRepository.deleteById(id)
            ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado")
        }
    }
}