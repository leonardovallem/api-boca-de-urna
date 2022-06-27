package com.jclr.apibocadeurna.repository

import com.jclr.apibocadeurna.model.Vote
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VoteRepository : CrudRepository<Vote, Int> {
    fun findFirstBy(): Vote?
}