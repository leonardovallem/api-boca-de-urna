package com.jclr.apibocadeurna.repository

import com.jclr.apibocadeurna.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?

    fun findByCpf(cpf: String): User?

    fun existsByCpf(cpf: String): Boolean
}