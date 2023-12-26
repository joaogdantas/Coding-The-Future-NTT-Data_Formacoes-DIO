package com.joaogdantas.NTTDataFormacoesDIO.web.controller

import com.joaogdantas.NTTDataFormacoesDIO.domain.user.User
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.dto.RegisterUserDTO
import com.joaogdantas.NTTDataFormacoesDIO.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userRepository: UserRepository

    private lateinit var user: User

    @PostMapping("/create")
    fun registerUser(@RequestBody data: RegisterUserDTO,
                     uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String> {

        val existentEmail: Optional<User> = userRepository.findByEmail(data.email)

        if(existentEmail.isPresent){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body("Já existe um usuário registrado com esse email, por favor escolha outro")
        }

        val existentLogin: Optional<User> = userRepository.findByLogin(data.login)

        if(existentLogin.isPresent){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body("Já existe um usuário registrado com esse login, por favor escolha outro")
        }

        val newUser = User(data)
        userRepository.save(newUser)

        val uri = uriComponentsBuilder
            .path("/users/{id}")
            .buildAndExpand(newUser.id)
            .toUri()

        return ResponseEntity.status(HttpStatus.CREATED.value()).body("Usuário registrado com sucesso")
    }

    @GetMapping("/all")
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): ResponseEntity<User> {
        val optionalUser = userRepository.findById(id)

        return if (optionalUser.isPresent) {
            return ResponseEntity.ok(optionalUser.get())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<String> {
        val optionalUser = userRepository.findById(id)

        return if (optionalUser.isPresent) {
            userRepository.deleteById(id)
            ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso")
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado")
        }
    }
}