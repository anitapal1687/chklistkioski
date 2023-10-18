package com.cs.ChklistKioski.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.ChklistKioski.model.DealConfiguration;
import com.cs.ChklistKioski.repository.DealConfigurationRepo;
import com.cs.ChklistKioski.service.DealService;

@RestController
public class DealConfigurationController {
	
	@Autowired
	private DealConfigurationRepo dealConfRepo;
	
	@Autowired
	private DealService service;

	   @PostMapping("/addDealConfigure")
	   ResponseEntity<?> addDealConfigure(@RequestParam String jsonString, @RequestParam String appId, @RequestParam int versionId) {

	       Document doc = Document.parse(jsonString);
	      
	       DealConfiguration dealConf= new DealConfiguration();
	       dealConf.setAppId(appId);
	       dealConf.setDealConfJson(doc);
	       dealConf.setVersion(versionId);
	       dealConfRepo.save(dealConf);
	       
	       return new ResponseEntity<>(null, HttpStatus.CREATED);
	   }
	   
	   @GetMapping("/getDealConfigure")
	   ResponseEntity<?> getDealConfigure( @RequestParam String appId, @RequestParam(required = false)  Integer versionId) {

	      
		   DealConfiguration dealConf= service.findAppId(appId, versionId);
	       
	       return new ResponseEntity<>(dealConf.getDealConfJson(), HttpStatus.OK);
	   }
	   
	   
	   @GetMapping("/getAllDealConfigure")
	   ResponseEntity<?> getAllDealConfigure() {

	      
	     List<DealConfiguration> dealConfList= dealConfRepo.findAll();
	     dealConfList= dealConfList.stream().sorted(Comparator.comparingInt(DealConfiguration::getVersion).reversed()).collect(Collectors.toList());
	  
	     dealConfList= dealConfList.stream().collect(Collectors.groupingBy(p -> p.getAppId())).values().stream()
	     .map(plans -> plans.stream().findFirst().get())
	     .collect(Collectors.toList());
	     List<Document> dealList= new ArrayList<>();
	     for(DealConfiguration deal: dealConfList) {
	    	 dealList.add(deal.getDealConfJson());
	     }
	    
	       return new ResponseEntity<>(dealList, HttpStatus.OK);
	   }
	   
	   
	   @GetMapping("/getAnyDealConfigure")
	   ResponseEntity<?> getAnyDealConfigure( @RequestParam  String key ,  @RequestParam  String value) {

	      
		   List<DealConfiguration> dealConfList= dealConfRepo.findAll();
		  
		  
		     dealConfList= dealConfList.stream().filter(p->(p.dealConfJson.toJson().contains(key) && p.dealConfJson.toJson().contains(value) ) ).collect(Collectors.toList());
		   
	    
	       return new ResponseEntity<>(dealConfList.get(0).getDealConfJson(), HttpStatus.OK);
	   }

}
