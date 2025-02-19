package org.schola.spring.economy.microservice.data.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.schola.spring.economy.microservice.data.repository.mongo")
public class MongoConfiguration { }
