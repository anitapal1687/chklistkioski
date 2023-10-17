package com.cs.ChklistKioski.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cs.ChklistKioski.model.DealConfiguration;



public interface DealConfigurationRepo extends MongoRepository<DealConfiguration, Integer> {

	
}
