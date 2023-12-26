package com.joaogdantas.NTTDataFormacoesDIO.web.controller

import com.joaogdantas.NTTDataFormacoesDIO.domain.content.ContentRepository
import com.joaogdantas.NTTDataFormacoesDIO.domain.content.EducationalContent
import com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto.RegisterContentDTO
import com.joaogdantas.NTTDataFormacoesDIO.domain.content.dto.ReturnContentDTO
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.Course
import com.joaogdantas.NTTDataFormacoesDIO.domain.course.CourseRepository
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/contents")
class ContentController {
    private lateinit var content: EducationalContent

    private lateinit var course: Course

    @Autowired
    private lateinit var contentRepository: ContentRepository

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @PostMapping("/create")
    fun registerContent(@RequestBody data: RegisterContentDTO,
                        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<String> {

        val existentTitle: Optional<EducationalContent> = contentRepository.findByTitle(data.title)

        if(existentTitle.isPresent){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body("Já existe um conteúdo com esse título, por favor escolha outro")
        }

        val newContent = EducationalContent(data)

        val existentCourse: Optional<Course> = courseRepository.findById(data.courseId)

        if(existentCourse.isEmpty){
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body("Não existe um curso com esse id, por favor escolha um existente")
        }

        newContent.course = existentCourse.get()

        contentRepository.save(newContent)

        val uri = uriComponentsBuilder
            .path("/contents/{id}")
            .buildAndExpand(newContent.id)
            .toUri()

        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Conteúdo registrado com sucesso")
    }

    @GetMapping("/all")
    fun getAllContents(): ResponseEntity<List<ReturnContentDTO>> {
        val contents = contentRepository.findAll()
        val contentDTOs = contents.map { ReturnContentDTO.fromEntity(it) }
        return ResponseEntity.ok(contentDTOs)
    }

    @GetMapping("/{id}")
    fun getContentById(@PathVariable id: Long): ResponseEntity<ReturnContentDTO> {
        val optionalContent = contentRepository.findById(id)

        return if (optionalContent.isPresent) {
            val contentDTO = ReturnContentDTO.fromEntity(optionalContent.get())
            ResponseEntity.ok(contentDTO)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("delete/{id}")
    fun deleteContent(@PathVariable id: Long): ResponseEntity<String> {
        val optionalContent = contentRepository.findById(id)

        return if (optionalContent.isPresent) {
            contentRepository.deleteById(id)
            ResponseEntity.status(HttpStatus.OK).body("Conteúdo deletado com sucesso")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conteúdo não encontrado")
        }
    }
}