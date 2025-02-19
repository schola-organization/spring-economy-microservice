package org.schola.spring.economy.microservice.router.controller

import org.schola.spring.economy.microservice.data.model.TransitionType

data class NewTransitionRequest(
    val type: TransitionType,
    val target: String,
    val amount: Double
)
