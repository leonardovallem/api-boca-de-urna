package com.jclr.apibocadeurna.service

import com.jclr.apibocadeurna.model.User
import com.jclr.apibocadeurna.repository.UserRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
    fun insert(user: User) = repository.save(user)

    fun cpfAlreadyRegistered(cpf: String) = repository.existsByCpf(cpf)

    fun get(email: String) = repository.findByEmail(email)

    fun correctPassword(password: String, user: User): Boolean {
        // TODO hash password
        return password == user.password
    }
}