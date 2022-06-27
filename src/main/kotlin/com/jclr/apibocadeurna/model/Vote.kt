package com.jclr.apibocadeurna.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
open class Vote(
    @Id
    open var id: Int = 0,
    open var lula: Long = 0,
    open var bolsonaro: Long = 0,
    open var ciro: Long = 0,
    open var simoneTebet: Long = 0,
    open var others: Long = 0,
    open var nullVotes: Long = 0
) {
    private fun updateCount(vote: VoteOption, subtract: Boolean = false): Vote {
        val add = if (subtract) -1 else 1
        when (vote) {
            VoteOption.LULA -> lula += add
            VoteOption.BOLSONARO -> bolsonaro += add
            VoteOption.CIRO -> ciro += add
            VoteOption.SIMONE_TEBET -> simoneTebet += add
            VoteOption.OTHER -> others += add
            VoteOption.NULL -> nullVotes += add
        }

        return this
    }

    fun update(newVote: VoteOption, previousVote: VoteOption) = updateCount(newVote, previousVote != VoteOption.NONE)
}