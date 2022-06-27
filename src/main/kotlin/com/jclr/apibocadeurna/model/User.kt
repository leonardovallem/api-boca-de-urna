package com.jclr.apibocadeurna.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "users")
open class User(
    @Column(nullable = false)
    open val name: String? = null,
    @Column(nullable = false, unique = true)
    open val cpf: String? = null,
    @Column(nullable = false, unique = true)
    open val email: String? = null,
    @Column(nullable = false)
    open val password: String? = null,
    @Column(nullable = false)
    open var vote: VoteOption? = VoteOption.NONE,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Int? = null
)

open class UserLoginData(open val email: String?, open val password: String?)
