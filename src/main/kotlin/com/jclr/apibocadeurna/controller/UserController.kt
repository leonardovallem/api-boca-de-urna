package com.jclr.apibocadeurna.controller

import com.jclr.apibocadeurna.model.User
import com.jclr.apibocadeurna.model.UserLoginData
import com.jclr.apibocadeurna.service.UserService
import com.jclr.apibocadeurna.util.responseOf
import com.jclr.apibocadeurna.util.success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {
    @PostMapping
    fun insert(user: User) = if (service.cpfAlreadyRegistered(user.cpf)) responseOf(
        403, "CPF already registered"
    ) else {
        service.insert(user)
        success()
    }

    @PostMapping
    fun login(user: UserLoginData): ResponseEntity<String?> {
        val retrievedUser = service.get(user.email) ?: return responseOf(400, "User not registered")

        return if (service.correctPassword(user.password, retrievedUser)) success()
        else responseOf(401, "Incorrect password")
    }
}