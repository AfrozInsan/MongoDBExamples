package com.nisum.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration 
public class MongoDBConfiguration {

	@Bean
	public MongoDbFactory getFactory() {
	 return new SimpleMongoDbFactory(new MongoClient(), "employee");
	}
	
	@Bean("mongoTemplate")
	public MongoTemplate getMongoTemplate() {
		MongoTemplate  mongoTemplate = new MongoTemplate(getFactory());
		return mongoTemplate;
	}
}
