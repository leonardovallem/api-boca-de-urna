package com.jclr.apibocadeurna.service

import com.jclr.apibocadeurna.model.Vote
import com.jclr.apibocadeurna.model.VoteOption
import com.jclr.apibocadeurna.repository.UserRepository
import com.jclr.apibocadeurna.repository.VoteRepository
import org.springframework.stereotype.Service

@Service
class VoteService(
    private val repository: VoteRepository,
    private val userRepository: UserRepository,
) {
    fun getVote() = repository.findFirstBy() ?: repository.save(Vote())

    fun vote(option: VoteOption, cpf: String): Boolean {
        userRepository.findByCpf(cpf)?.run {
            val retrievedVote = repository.findFirstBy() ?: Vote()
            val updatedVote = retrievedVote.update(option, vote!!)
            repository.save(updatedVote)

            vote = option
            userRepository.save(this)
        } ?: return false
        return true
    }
}