package org.schola.spring.economy.microservice.data.service

import org.schola.spring.economy.microservice.data.model.Purse
import org.schola.spring.economy.microservice.data.model.TRANSITION_KEY
import org.schola.spring.economy.microservice.data.model.Transition
import org.schola.spring.economy.microservice.data.repository.TransitionMongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.awt.print.Pageable

@Service
class TransitionService {

    @Autowired
    private lateinit var mongo: TransitionMongoRepository

    @CachePut(value = [TRANSITION_KEY], key = "#transition.id")
    fun save(transition: Transition) {
        mongo.save(transition)
    }

    @Cacheable(value = [TRANSITION_KEY], key = "#identifier")
    fun get(identifier: String): Transition = mongo.findById(identifier).orElse(null)

    @Cacheable(value = [TRANSITION_KEY])
    fun getAll(pageable: org.springframework.data.domain.Pageable): List<Transition> = mongo.findAll(pageable).content

    fun getWithPurse(purse: Purse): List<Transition> = mongo.findByPurse(purse)

}