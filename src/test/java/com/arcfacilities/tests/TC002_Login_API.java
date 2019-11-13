package com.arcfacilities.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arcfacilities.accelerator.ActionEngine;
import com.arcfacilities.utilities.Encryptjava;
import com.arcfacilities.utilities.TestUtil;
import com.jayway.restassured.response.Header;

public class TC002_Login_API extends ActionEngine {
	// Logger initialization for the class
	private static final Logger LOG = Logger
			.getLogger(TC001_Registration_API.class);

	@DataProvider(name = "LoginCall")
	public Object[][] LoginCall() {
		return TestUtil.getData("LoginCall", dataPath, "WebService");
	}

	@Test(dataProvider = "LoginCall", priority = 1)
	public void verify_Login_Call(Hashtable<String, String> data)
			throws InterruptedException {

		RestAssured.baseURI = data.get("BaseURI");

		// Request object
		httpRequest = RestAssured.given();

		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();

		System.out
				.println("****************************************************");
		// String decript = Encryptjava.AESEncrypt("Hello@123",
		// "av45k1pfb024xa3bl359vsb4esortvks74sksr5oy4s5serondry84jsrryuhsr5ys49y5seri5shrdliheuirdygliurguiy5ru");
		// System.out.println("****************************************************"+decript);

		// requestParams.put(data.get("Param1"), data.get("ParamValue1"));
		// requestParams.put(data.get("Param2"), data.get("ParamValue2"));
		// requestParams.put(data.get("Param3"), data.get("ParamValue3"));
		// httpRequest.header(data.get("Param1"), data.get("ParamValue1"));
		// httpRequest.header(data.get("Param2"), data.get("ParamValue2"));
		// httpRequest.header(data.get("Param3"), data.get("ParamValue3"));

		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("LoginId",
				"CvoVvaFydCeqYuKpulObW2Lcrx8rg8Qef5zaWdMzsRU=");
		httpRequest.header("Password", "YdhqoB0oMVVB8gzsUqmLkg==");
		httpRequest.header("csrc", "lpV6PI2WqrG9JMlPPmaisw==");

		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.post(data.get("ResourcePath"));

		// Thread.sleep(3000);

		Thread.sleep(3000);
		System.out
				.println("*****************************************************");
		System.out.println("Status code = " + response.getStatusCode());
		System.out
				.println("*****************************************************");
		System.out.println("Responce header is = "
				+ response.headers().asList());
		System.out
				.println("*****************************************************");
		System.out.println("Responce body is = " + response.body().asString());
		System.out
				.println("*****************************************************");

	}

	/*
	 * @DataProvider(name = "LoginStatus") public Object[][] LoginStatus() {
	 * return TestUtil.getData("LoginStatus", dataPath, "WebService"); }
	 * 
	 * @Test(dataProvider="LoginStatus", priority = 2) public void
	 * verify_Login_Status(Hashtable<String, String> data) {
	 * 
	 * //JsonPath jsonPathEvaluator = response.jsonPath();
	 * 
	 * assertValues("Status Code" , data.get("StatusCode"),
	 * response.getStatusCode());
	 * 
	 * assertValues("Content Type", data.get("ContentType"),
	 * getStringFrom(response.getContentType())); }
	 */

	/*
	 * public static String createAuthToken(String baseRestURL, HttpClient
	 * httpClient, String username, String password){ String APIPath =
	 * "/api/auth/login"; String completeRestURL = baseRestURL + APIPath;
	 * System.out.println("REST API URL: " + completeRestURL);
	 * 
	 * // Define the server endpoint to send the HTTP request to URL serverUrl;
	 * try { serverUrl = new URL(completeRestURL);
	 * 
	 * HttpPost httpRequest = new HttpPost(completeRestURL);
	 * httpRequest.setHeader("Content-Type", "application/json");
	 * httpRequest.setHeader("Accept", "application/json"); StringEntity body
	 * =new
	 * StringEntity("{\"username\": \""+username+"\",\"password\": \""+password
	 * +"\",\"loginMode\": 1,\"applicationType\": 35}");
	 * httpRequest.setEntity(body);
	 * 
	 * HttpResponse response = httpClient.execute(httpRequest);
	 * 
	 * Header[] headers = (Header[]) response.getAllHeaders(); for (int i = 0; i
	 * < headers.length; i++){ Header header = headers[i];
	 * //System.out.println(header.getName() + " : " + header.getValue()); if
	 * (header.getName().equalsIgnoreCase("X-MSTR-AuthToken")){ return
	 * header.getValue(); } }
	 * 
	 * return null;
	 * 
	 * } catch (MalformedURLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return null; }
	 */

}
