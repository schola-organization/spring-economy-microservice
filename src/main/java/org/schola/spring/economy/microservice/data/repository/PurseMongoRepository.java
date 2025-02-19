package org.schola.spring.economy.microservice.data.repository;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurseMongoRepository extends MongoRepository<Purse, String> { }
