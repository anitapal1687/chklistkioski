package com.cs.ChklistKioski.controller;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.ChklistKioski.model.DealConfiguration;
import com.cs.ChklistKioski.model.DealRequest;
import com.cs.ChklistKioski.repository.DealRequestRepo;
import com.cs.ChklistKioski.service.DealService;

@RestController
public class DealRequestController {
	
	
	@Autowired
	DealRequestRepo dealRequestRepo;
	
	@Autowired
	DealService dealService;
	
	

	 @PostMapping("/addDealRequest")
	   ResponseEntity<?> addDealRequest(@RequestParam String dealRequest, @RequestParam String dealId) {

	       Document doc = Document.parse(dealRequest);
	     
	       DealRequest dealReq= new DealRequest();
	       dealReq.setDealReq(doc);
	       dealReq.setDealId(dealId);
	      
	       dealRequestRepo.save(dealReq);
	       
	       return new ResponseEntity<>(null, HttpStatus.CREATED);
	   }
	 
	 
	 @GetMapping("/getDealRequest")
	   ResponseEntity<?> getDealRequest(@RequestParam String dealId) {

		 DealRequest dealRequest= dealService.findDealId(dealId);

	       
	       return new ResponseEntity<>(dealRequest, HttpStatus.OK);
	   }
	 
		/*
		 * @GetMapping("/getDealRequestyAnyParam") ResponseEntity<?>
		 * getDealAnyParam(@RequestParam String param, @RequestParam String value) {
		 * 
		 * DealRequest dealRequest= dealService.findByAnyParam(param, value);
		 * 
		 * 
		 * return new ResponseEntity<>(dealRequest, HttpStatus.OK); }
		 */
}
