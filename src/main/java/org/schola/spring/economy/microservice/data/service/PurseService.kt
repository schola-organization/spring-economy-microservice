package org.schola.spring.economy.microservice.data.service

import org.schola.spring.economy.microservice.data.model.PURSE_KEY
import org.schola.spring.economy.microservice.data.model.Purse
import org.schola.spring.economy.microservice.data.repository.PurseMongoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PurseService {

    @Autowired
    private lateinit var mongo: PurseMongoRepository

    @CachePut(value = [PURSE_KEY], key = "#purse.id")
    fun save(purse: Purse) {
        mongo.save(purse)
    }

    @Cacheable(value = [PURSE_KEY], key = "#identifier")
    fun get(identifier: String): Purse = mongo.findById(identifier).orElse(null)

    @Cacheable(value = [PURSE_KEY])
    fun getAll(pageable: Pageable): List<Purse> = mongo.findAll(pageable).content

}
