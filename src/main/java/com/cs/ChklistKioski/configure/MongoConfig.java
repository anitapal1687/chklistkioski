package com.cs.ChklistKioski.configure;

import java.io.IOException;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClientURI;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientSettings;

import com.mongodb.MongoCredential;
import com.mongodb.MongoClient;

import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

@Configuration
@EnableConfigurationProperties
public class MongoConfig {
	
	

	    @ConfigurationProperties
	    public MongoProperties primaryProperties() {
	        return new MongoProperties();
	    }

	    @Bean
	    public MongoClient mongoClient( MongoProperties mongoProperties) {

	    /*    MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(), mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

	        return MongoClients.create(MongoClientSettings.builder()
	          .applyToClusterSettings(builder -> builder.hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
	          .credential(credential)
	          .build());
	        */
	        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://anitapalmongo:4IekKYAhRHRcP1ViAMOdgyvj7egM4aS9O6ZbQpyykTOSbpg0XwKw9Fs7a0tDfqESzmrKe9SXzpJgACDb87gphw==@anitapalmongo.mongo.cosmos.azure.com:10255/?ssl=true&retrywrites=false&replicaSet=globaldb&maxIdleTimeMS=120000&appName=@anitapalmongo@"));
	    	
	    	
	       return mongoClient;
	    }

		
		 

	    @Bean
	    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
	        return new MongoTemplate(mongoClient,"Deals");
	    }
}
