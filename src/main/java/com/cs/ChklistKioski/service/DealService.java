package com.cs.ChklistKioski.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators;
import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators.Max;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.cs.ChklistKioski.model.DealConfiguration;
import com.cs.ChklistKioski.model.DealRequest;


@Service
public class DealService {
	
	  @Autowired
	    MongoTemplate mongoTemplate;
	  
		public DealConfiguration findAppId(String appId, Integer versionId) {
			List<DealConfiguration> conList = null;
			DealConfiguration con = new DealConfiguration();

			if (versionId != null) {
				Query query = new Query(
						Criteria.where("appId").is(appId).andOperator(Criteria.where("version").is(versionId)));
				conList = mongoTemplate.find(query, DealConfiguration.class);
				con = conList != null &&  conList.size()!=0? conList.get(0) : null;
			} else {
				Query query = new Query(Criteria.where("appId").is(appId));
				conList = mongoTemplate.find(query, DealConfiguration.class);
				con = conList != null && conList.size() > 1 ? conList.get(conList.size() - 1)
						: conList.size() == 1 ? conList.get(0) : null;
			}

			return con;
		}
		
		public DealRequest findDealId(String dealId) {
			List<DealRequest> reqList = null;
			DealRequest con = new DealRequest();


				Query query = new Query(
						Criteria.where("dealId").is(dealId));
				reqList = mongoTemplate.find(query, DealRequest.class);
				con = reqList != null ? reqList.get(0) : null;
			
			return con;
		}
		
		
	


}
