package com.jclr.apibocadeurna.controller

import com.jclr.apibocadeurna.model.VoteOption
import com.jclr.apibocadeurna.service.VoteService
import com.jclr.apibocadeurna.util.responseOf
import com.jclr.apibocadeurna.util.success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vote")
class VoteController(private val service: VoteService) {
    @PostMapping
    fun vote(vote: VoteOption, cpf: String): ResponseEntity<String?> {
        return if (service.vote(vote, cpf)) success() else responseOf(
            401, "User's CPF not found"
        )
    }
}