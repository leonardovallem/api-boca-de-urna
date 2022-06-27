package com.jclr.apibocadeurna.controller

import com.jclr.apibocadeurna.model.User
import com.jclr.apibocadeurna.model.UserLoginData
import com.jclr.apibocadeurna.service.UserService
import com.jclr.apibocadeurna.util.responseOf
import com.jclr.apibocadeurna.util.success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {
    @PostMapping("/register")
    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    fun insert(@RequestBody user: User) = if (service.cpfAlreadyRegistered(user.cpf!!)) responseOf(
        403, "CPF já cadastrado"
    ) else {
        service.insert(user)
        success()
    }

    @PostMapping("/login")
    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    fun login(@RequestBody user: UserLoginData): ResponseEntity<String?> {
        if (user.email == null) return responseOf(
            400,
            "O campo de email não pode ficar vazio"
        )
        if (user.password == null) return responseOf(
            400,
            "O campo de senha não pode ficar vazio"
        )

        val retrievedUser = service.get(user.email!!) ?: return responseOf(400, "Usuário não cadastrado")

        return if (service.correctPassword(user.password!!, retrievedUser)) success(retrievedUser.cpf)
        else responseOf(401, "A senha inserida é incorreta")
    }

    @GetMapping("/{cpf}")
    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    fun getUserVote(@PathVariable(required = true) cpf: String) = service.getByCpf(cpf)?.let {
        success(it.vote!!.toString())
    } ?: responseOf(400, "Usuário não cadastrado")
}