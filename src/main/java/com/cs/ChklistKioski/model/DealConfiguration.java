package com.cs.ChklistKioski.model;


import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Document(collection = "Deal_Configuration")
public class DealConfiguration {

	public org.bson.Document dealConfJson;
	

	int version;
	
	String appId;

	
	public org.bson.Document getDealConfJson() {
		return dealConfJson;
	}

	public void setDealConfJson(org.bson.Document dealConfJson) {
		this.dealConfJson = dealConfJson;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}

