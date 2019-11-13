package com.arcfacilities.tests;

import io.restassured.RestAssured;

import java.util.Hashtable;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arcfacilities.accelerator.ActionEngine;
import com.arcfacilities.utilities.TestUtil;

public class TC002_SSO_login extends ActionEngine {
	
	
	
	@DataProvider(name = "SSOCall")
	public Object[][] SSOCall() {
		return TestUtil.getData("SSOCall", dataPath, "WebService");
	}

	@Test(dataProvider = "SSOCall", priority = 1)
	public void verify_SSOCall(Hashtable<String, String> data) throws InterruptedException {

		RestAssured.baseURI = data.get("BaseURI");
		
		// Request object
		httpRequest = RestAssured.given();
		
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put(data.get("Param1"), data.get("ParamValue1"));
		//requestParams.put(data.get("Param2"), data.get("ParamValue2"));	
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());	
		
		response = httpRequest.post(data.get("ResourcePath"));
		
		Thread.sleep(3000);
	}
	
	
	@DataProvider(name = "SSOStatus")
	public Object[][] LoginStatus() {
		return TestUtil.getData("SSOStatus", dataPath, "WebService");
	}
	
	@Test(dataProvider="SSOStatus", priority = 2)
	public void verify_SSOStatus(Hashtable<String, String> data) {
		
		//JsonPath jsonPathEvaluator = response.jsonPath();
		
		assertValues("Status Code" , data.get("StatusCode"), response.getStatusCode());
		
		assertValues("Content Type", data.get("ContentType"), getStringFrom(response.getContentType()));
	}
	

}
