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
    final fun getVote(): Vote = repository.findById(0).run {
        return if (isPresent) get() else {
            repository.save(Vote())
            getVote()
        }
    }

    fun vote(option: VoteOption, cpf: String): Boolean {
        userRepository.findByCpf(cpf)?.apply {
            repository.save(getVote().update(option, vote))
        } ?: return false
        return true
    }
}