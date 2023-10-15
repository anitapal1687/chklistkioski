package com.cs.ChklistKioski.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChkListKioskiController {
	
	
	@Autowired
	   private MongoTemplate mongoTemplate;
	

	   @PostMapping("/addProject")
	   ResponseEntity<?> add(@RequestParam String jsonString, @RequestParam String projectName) {

	       Document doc = Document.parse(jsonString);
	       mongoTemplate.insert(doc, projectName);

	       return new ResponseEntity<>(null, HttpStatus.CREATED);
	   }

	   

	   @GetMapping("/getProject")
	   ResponseEntity<?> getProject( @RequestParam String projectName) {

	      
		   List<String> result = mongoTemplate.findAll(String.class, projectName);
		  // Gson gson = new Gson();    
		   //Json content = gson.toJson(list);
	       return new ResponseEntity<>(result.get(0), HttpStatus.OK);
	   }
	
}
