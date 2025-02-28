package org.schola.spring.economy.microservice.data.repository;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.schola.spring.economy.microservice.data.model.Transition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitionMongoRepository extends MongoRepository<Transition, String> {

    List<Transition> findByPurse(Purse purse);

}
