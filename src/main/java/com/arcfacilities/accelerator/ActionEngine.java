package com.arcfacilities.accelerator;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.arcfacilities.reports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

import freemarker.template.utility.NullArgumentException;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

/**
 * @author prash
 *
 */
public class ActionEngine extends TestBase{
	
	// Logger initialization for the class
	private static final Logger LOG = Logger.getLogger(ActionEngine.class);
	
	/**
	 * @param response
	 * @return
	 */
	protected final JsonPath getJsonPath(final Response response) {
		JsonPath path = null;
		try {
			String jsonString = response.body().asString();
			path = new JsonPath(jsonString);
		} catch (Exception exception) {
			LOG.debug("Exception Occured | getJsonPath", exception);
		}
		return path;
	}
	
	/**
	 * @param response
	 * @return
	 */
	protected final XmlPath getXmlPath(final Response response) {
		XmlPath path = null;
		try {
			String xmlString = response.body().asString();
			path = new XmlPath(xmlString);
		} catch (Exception exception) {
			LOG.debug("Exception Occured | getXmlPath", exception);
		}
		return path;
	}
	
	/**
	 * @param response
	 * @return
	 */
	protected final List<Header> getHeaderAsList(final Response response) {
		List<Header> headerList = null;
		try {
			headerList = response.getHeaders().asList();
		} catch (Exception exception) {
			LOG.debug("Exception Occured | getHeaderAsList", exception);
		}
		return headerList;
	}
	
	/**
	 * @param response
	 * @return
	 */
	protected final Map<String, String> getCoockiesAsMap(final Response response) {
		Map<String, String> coockiesMap = null;
		try {
			coockiesMap = response.cookies();
		} catch (Exception exception) {
			LOG.debug("Exception Occured | getCoockiesAsMAp", exception);
		}
		return coockiesMap;
	}
	
	/**
	 * @param response
	 * @param objectPath
	 * @return
	 */
	protected final ArrayList<Map<String, ?>> getListOfItemOfObjectPath(final Response response , final String objectPath) {
		JsonPath path = null;
		ArrayList<Map<String, ?>> itemList = null;
		try {
			if (response != null && objectPath != null) {
				path = getJsonPath(response);
				if (path != null) {
					itemList = path.get(objectPath);
				} else {
					throw new IllegalArgumentException("Unable to locate any object for the path " + objectPath);
				}
				
			} else {
				throw new IllegalArgumentException("Response or Object Path argument is Null");
			}
		} catch (ClassCastException | IllegalArgumentException exception) {
			LOG.debug("Exception Occured | getListOfItemOfObjectPath", exception);
		}
		return itemList;
	}
	
	/**
	 * @param response
	 * @param objectPath
	 * @return
	 */
	protected final int getCountOfItemOfObjectPath(final Response response , final String objectPath) {
		JsonPath path = null;
		int itemCount = 0;
		try {
			if (response != null && objectPath != null) {
				path = getJsonPath(response);
				if (path != null) {
					itemCount = path.get(objectPath + ".size()");
				} else {
					throw new IllegalArgumentException("Unable to locate any object for the path " + objectPath);
				}
				
			} else {
				throw new IllegalArgumentException("Response or Object Path argument is Null");
			}
		} catch (ClassCastException | NullArgumentException exception) {
			LOG.debug("Exception Occured | getCountOfItemOfObjectPath", exception);
		}
		return itemCount;
	}
	
	
	/**
	 * @param itemToBeConverted
	 * @return
	 */
	protected final String getStringFrom(final Object itemToBeConverted) {
		String value = null;
		try {
			if (itemToBeConverted != null) {
				value = String.valueOf(itemToBeConverted);
				if(value.contains(".")) {
					value = value.substring(0, value.length()-2);
				}
				System.out.println("Value : " + value);
			} else {
				throw new NullArgumentException("Item to be converted can't be null");
			}
			
		} catch (NullArgumentException exception) {
			LOG.debug("Exception Occured | getStringFrom", exception);
		}
		return value;
	}
	
	/**
	 * @param argumentIdentifier
	 * @param expectedValue
	 * @param actualvalue
	 * @return
	 */
	protected final boolean assertValues(final String argumentIdentifier, final Object expectedValue , final Object actualvalue) {
		boolean flag = false;
		try {
			if (expectedValue != null && actualvalue != null) {
				if (expectedValue.equals(actualvalue)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Expected " + argumentIdentifier  + " " + expectedValue , "Actual " + argumentIdentifier  + " " + actualvalue );
					flag = true;
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Expected " + argumentIdentifier  + " " + expectedValue , "Actual " + argumentIdentifier  + " " + actualvalue );
					Assert.fail();
				}
			} else {
				throw new IllegalArgumentException("Expected or Actual value can't be null");
			}
		} catch (IllegalArgumentException exception) {
			LOG.debug("Exception Occured | assertValues", exception);
		}
		return flag;
	}
	
	
	/**
	 * @param argumentIdentifier
	 * @param expectedValue
	
	 * @return
	 */
	public final boolean AssertValuesGreaterthan(final String argumentIdentifier, final int expectedValue ) {
		boolean flag = false;
		try {
			if (expectedValue > 0) {				
					ExtentTestManager.getTest().log(LogStatus.PASS, "Expected " + argumentIdentifier  + " " + expectedValue  );
					flag = true;
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Expected " + argumentIdentifier  + " " + expectedValue );
				}
			
		} catch (IllegalArgumentException exception) {
			LOG.debug("Exception Occured | assertValues", exception);
		}
		return flag;
	}
	
	/**
	 * @param portNo
	 * @return
	 */
	
	public final int getIntegerFromString(final String portNo) {
		int port = 0;
		String newPort = null;
		try {
			if (portNo != null) {
				
				if(portNo.contains(".")) {
					newPort = portNo.substring(0, portNo.length()-2);
				}
				port = Integer.valueOf(newPort);
			} else {
				throw new IllegalArgumentException("Argument can't be null");
			}
		} catch (IllegalArgumentException exception) {
			LOG.debug("Exception Occured | getIntegerFromString", exception);
		}
		return port;
	}
	
	/**
	 * @param response
	 * @param itemPath
	 * @return
	 */
	protected final Object getItemFromReponse(final Response response ,final String itemPath) {
		Object item = null;
		try {
			if (response != null && itemPath != null) {
				JsonPath jsonPath = getJsonPath(response);
				item = jsonPath.get(itemPath);
			} else {
				throw new IllegalArgumentException("Arguments can't be null");
			}
		} catch (IllegalArgumentException exception) {
			LOG.debug("Exception Occured | getItemFromReponse" , exception);
		}
		return item;
	}
	
	/**
	 * @param data
	 * @return
	 */
	protected final String getSeesonKey(final Hashtable<String, String> data) {
		String sessionKey = null;
		Response responseObj = null;
		try {
			RestAssured.baseURI = data.get("BaseURI");
			RestAssured.port = getIntegerFromString(data.get("URIPort"));
			responseObj = given().
				headers(data.get("Param1"), data.get("ParamValue1")).
				body(data.get("RequestBodyAuth")).
			when().
				post(data.get("ResourcePathAuth")).
			then().
				statusCode(200).
			extract().response();

			String jsonIDString = (String) getItemFromReponse(responseObj, "session.name");
			String jsonIDValue = (String) getItemFromReponse(responseObj, "session.value");
			ExtentTestManager.getTest().log(LogStatus.INFO, "Cookie Information" , jsonIDString + " = " + jsonIDValue);
			sessionKey = jsonIDValue;
		} catch (Exception exception) {
			LOG.debug("Exception Occured | getSeesonKey", exception);
		}
		return sessionKey.trim();
	}
}