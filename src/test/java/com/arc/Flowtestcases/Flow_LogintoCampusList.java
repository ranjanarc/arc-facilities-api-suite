package com.arc.Flowtestcases;

import io.restassured.RestAssured;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arcfacilities.accelerator.ActionEngine;
import com.arcfacilities.tests.TC001_Registration_API;
import com.arcfacilities.utilities.TestUtil;

public class Flow_LogintoCampusList extends ActionEngine {

	// Logger initialization for the class
	private static final Logger LOG = Logger.getLogger(Flow_LogintoCampusList.class);

	@DataProvider(name = "LoginTest")
	public Object[][] LoginTest() {
		return TestUtil.getData("LoginTest", dataPath, "WebService");
	}
	


	@Test(dataProvider = "LoginTest", priority = 1)
	public void LoginTest(Hashtable<String, String> data) throws InterruptedException {

		RestAssured.baseURI = data.get("BaseURI");
		
		// Request object
		httpRequest = RestAssured.given();
		
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();

		requestParams.put(data.get("Param1"), data.get("ParamValue1"));
		requestParams.put(data.get("Param2"), data.get("ParamValue2"));
		requestParams.put(data.get("Param3"), data.get("ParamValue3"));
		
		//httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.post(data.get("ResourcePath"));
		
		Thread.sleep(3000);
	}




}


