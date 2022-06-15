package com.jclr.apibocadeurna.repository

import com.jclr.apibocadeurna.model.Vote
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VoteRepository : CrudRepository<Vote, Int> {
}