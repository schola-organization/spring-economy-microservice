package org.schola.spring.economy.microservice.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.redis.core.RedisHash

const val PURSE_KEY = "purse"

@Document(PURSE_KEY)
@RedisHash(PURSE_KEY)
data class Purse(

    @Id
    val id: String,
    val amount: Double

)