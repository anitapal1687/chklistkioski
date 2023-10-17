package com.cs.ChklistKioski.controller;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

@RestController
public class ChkListKioskiController {
	
	
	@Autowired
	   private MongoTemplate mongoTemplate;
	
	@Autowired
	private MongoClient mongoClient;

	   @PostMapping("/addProject")
	   ResponseEntity<?> add(@RequestParam String jsonString, @RequestParam String projectName) {

	       Document doc = Document.parse(jsonString);
	       List<String> result = mongoTemplate.findAll(String.class, projectName);
           
	       if(result!=null && result.get(0)!=null) {
	    	   MongoDatabase database = mongoClient.getDatabase("Project");
	           MongoCollection<Document> collection = database.getCollection(projectName);

	           Document query = new Document().parse(jsonString);
	           Bson updates = Updates.combine(
	                   Updates.set("runtime", 99),
	                   Updates.addToSet("genres", "Sports"),
	                   Updates.currentTimestamp("lastUpdated"));
	           UpdateOptions options = new UpdateOptions().upsert(true);
	    	    UpdateResult result1 = collection.updateOne(query, updates, options);
	       } else {
	    	   mongoTemplate.insert(doc, projectName);
	       }

	       return new ResponseEntity<>(null, HttpStatus.CREATED);
	   }

	   

	   @GetMapping("/getProject")
	   ResponseEntity<?> getProject( @RequestParam String projectName) {

	      
		   List<String> result = mongoTemplate.findAll(String.class, projectName);
		   int count=result.size();
		  // Gson gson = new Gson();    
		   //Json content = gson.toJson(list);
	       return new ResponseEntity<>(result.get(count-1), HttpStatus.OK);
	   }
	
}
