package com.jclr.apibocadeurna.controller

import com.jclr.apibocadeurna.model.UserVote
import com.jclr.apibocadeurna.model.Vote
import com.jclr.apibocadeurna.model.VoteOption
import com.jclr.apibocadeurna.service.VoteService
import com.jclr.apibocadeurna.util.responseOf
import com.jclr.apibocadeurna.util.success
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vote")
class VoteController(private val service: VoteService) {
    @PostMapping
    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    fun vote(@RequestBody vote: UserVote): ResponseEntity<String?> {
        if (vote.cpf == null) return responseOf(
            400,
            "CPF foi enviado nulo"
        )
        if (vote.vote == null) return responseOf(
            400,
            "o voto foi enviado nulo"
        )

        return if (service.vote(vote.vote!!, vote.cpf!!)) success() else responseOf(
            401, "CPF não cadastrado"
        )
    }

    @GetMapping
    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    fun retrieveData() = ResponseEntity<Vote>(service.getVote(), null, 200)
}