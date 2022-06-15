package com.jclr.apibocadeurna.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Vote(
    @Id
    val id: Int = 0,
    val lula: Long = 0,
    val bolsonaro: Long = 0,
    val ciro: Long = 0,
    val simoneTebet: Long = 0,
    val others: Long = 0,
    val nullVotes: Long = 0
) {
    private fun updateCount(vote: VoteOption, subtract: Boolean = false): Vote {
        val add = if (subtract) -1 else 1
        return when (vote) {
            VoteOption.LULA -> copy(lula = lula + add)
            VoteOption.BOLSONARO -> copy(bolsonaro = bolsonaro + add)
            VoteOption.CIRO -> copy(ciro = ciro + add)
            VoteOption.SIMONE_TEBET -> copy(simoneTebet = simoneTebet + add)
            VoteOption.OTHER -> copy(others = others + add)
            VoteOption.NULL -> copy(nullVotes = nullVotes + add)
            else -> this
        }
    }

    fun update(newVote: VoteOption, previousVote: VoteOption) = updateCount(newVote, previousVote != VoteOption.NONE)
}