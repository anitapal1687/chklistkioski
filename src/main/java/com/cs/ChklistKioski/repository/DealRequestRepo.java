package com.cs.ChklistKioski.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cs.ChklistKioski.model.DealConfiguration;
import com.cs.ChklistKioski.model.DealRequest;

public interface DealRequestRepo  extends MongoRepository<DealRequest, Integer>  {

}
