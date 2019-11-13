package com.arcfacilities.tests;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arcfacilities.accelerator.ActionEngine;
import com.arcfacilities.utilities.TestUtil;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TC001_Registration_API extends ActionEngine {

	// Logger initialization for the class
	private static final Logger LOG = Logger.getLogger(TC001_Registration_API.class);

	@DataProvider(name = "RegistrationCall")
	public Object[][] RegistrationCall() {
		return TestUtil.getData("RegistrationCall", dataPath, "WebService");
	}

	@Test(dataProvider = "RegistrationCall", priority = 1)
	public void registrationCall(Hashtable<String, String> data) throws InterruptedException {

		RestAssured.baseURI = data.get("BaseURI");
		
		// Request object
		httpRequest = RestAssured.given();
		
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();

		requestParams.put(data.get("Param1"), data.get("ParamValue1"));
		requestParams.put(data.get("Param2"), data.get("ParamValue2"));
		requestParams.put(data.get("Param3"), data.get("ParamValue3"));
		requestParams.put(data.get("Param4"), data.get("ParamValue4"));
		requestParams.put(data.get("Param5"), data.get("ParamValue5"));
		requestParams.put(data.get("Param6"), data.get("ParamValue6"));
		requestParams.put(data.get("Param7"), data.get("ParamValue7"));
		requestParams.put(data.get("Param8"), data.get("ParamValue8"));

		
		/*Header h1= new Header(data.get("Content-Type"), "application/json");
	    Header h2 = new Header("Accept-Language", "en-US");
	    Header h3 = new Header("Accept-Encoding", "gzip");
	    List<Header> list = new ArrayList<Header>();
	    list.add(h1);
	    list.add(h2);
	    list.add(h3);
	    Headers header = new Headers(list);
	    httpRequest.headers(header);*/
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Accept-Language", "en-us");
		httpRequest.header("Accept-Encoding", "gzip");

		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.post(data.get("ResourcePath"));
		
		Thread.sleep(3000);
		System.out.println("*****************************************************");
		System.out.println("Status code = " + response.getStatusCode());
		System.out.println("*****************************************************");
		System.out.println("Responce header is = "+ response.headers().asList());
		System.out.println("*****************************************************");
		System.out.println("Responce body is = " + response.body().asString());
		System.out.println("*****************************************************");
		
		
		
		
	}
	
	
	
	
	
	
	
	
@Test(dataProvider="RegistrationStatus", priority = 2)
public void registrationStatus(Hashtable<String, String> data) {
		System.out.println("#############################\n\n\n" + response.body().asString());
		
		JsonPath jsonPathEvaluator = response.jsonPath();
        Object PWUserID = jsonPathEvaluator.get("PWUserID");
        Object PWAccountID = jsonPathEvaluator.get("PWAccountID");
        Object PWContactID = jsonPathEvaluator.get("PWContactID");
        //String email1 = jsonPathEvaluator.get("Email");
        String firstname = jsonPathEvaluator.get("FirstName");
        String Firstname = data.get("ParamValue1");
        System.out.println("1 = "+firstname); 
        System.out.println("2 = "+Firstname); 
        //System.out.println("PWContactID = "+PWContactID); 
        
        int email = Integer.parseInt(data.get("Param1"));
		int Datasheet_Responce = Integer.parseInt(data.get("StatusCode"));
        int Responce = response.getStatusCode();
     
     // Validation of Email
       // assertValues("Email" , email1, email);
     // Validation of Email
       // assertValues("First Name" , firstname, Firstname);      
        
     // Validation of responce code
        assertValues("Status Code" , Datasheet_Responce, Responce);
     // Validation of Content Type
		assertValues("Content Type", data.get("ContentType"), getStringFrom(response.getContentType()));
	 // Validation of PWUserID
		assertValuesGreaterthan("PWUserID",PWUserID);
	 // Validation of PWAccountID
		assertValuesGreaterthan("PWAccountID",PWAccountID);
	 // Validation of PWContactID
		assertValuesGreaterthan("PWContactID",PWContactID);
	}
	
	private void assertValuesGreaterthan(String string, Object pWUserID) {
	// TODO Auto-generated method stub
	
}

	@DataProvider(name = "RegistrationStatus")
	public Object[][] RegistrationStatus() {
		return TestUtil.getData("RegistrationStatus", dataPath, "WebService");
	}
}
