package com.cs.ChklistKioski.model;



import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Deal_Request")
public class DealRequest {

	String dealId;
	
	org.bson.Document dealReq;
	
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public org.bson.Document getDealReq() {
		return dealReq;
	}
	public void setDealReq(org.bson.Document dealReq) {
		this.dealReq = dealReq;
	}
}
