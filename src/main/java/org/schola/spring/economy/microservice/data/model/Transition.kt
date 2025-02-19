package org.schola.spring.economy.microservice.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.redis.core.RedisHash

const val TRANSITION_KEY = "transition"

@Document(TRANSITION_KEY)
@RedisHash(TRANSITION_KEY)
data class Transition(

    @Id
    val id: String,

    val type: TransitionType,

    val amount: Double

)

enum class TransitionType {
    DEPOSIT,
    WITHDRAW
}
