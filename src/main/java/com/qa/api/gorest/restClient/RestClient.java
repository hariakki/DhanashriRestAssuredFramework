package com.qa.api.gorest.restClient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http methods which will call the apis and having generic methods for getting the response and fetch
 * the values from the response.
 * @author Dhanashri
 *
 */

	//HTTP Methods - GET, POST, PUT, DELETE

public class RestClient {
	
	/**
	 * This method calls the GET API
	 * @param baseURI
	 * @param contentType
	 * @param basePath
	 * @param token
	 * @param queryParamMap
	 * @param log
	 * @return This method is returning the response from the GET Call.
	 */
	public static Response doGet(String baseURI, String contentType, String basePath, Map<String, String> token, 
			Map<String, String> queryParamMap, boolean log ) {	
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType,token,queryParamMap,log);
			return getResponse("GET", request, basePath);
		}
		return null;
	}
	
	/**
	 * This method calls the POST API
	 * @param baseURI
	 * @param contentType
	 * @param basePath
	 * @param token
	 * @param queryParamMap
	 * @param log
	 * @param obj
	 * @return This method is returning the response from the POST Call.
	 */
	public static Response doPost(String baseURI, String contentType, String basePath, Map<String, String> token, 
			Map<String, String> queryParamMap, boolean log, Object obj) {	
		
		if(setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType, token, queryParamMap, log);
			/*
			 * String jsonPayload=TestUtil.getserializedJSON(obj);
			 * request.body(jsonPayload);
			 */
			return getResponse("POST", request, basePath);
		}
		return null;
	}
	
	public static void addRequestPayload(RequestSpecification request, Object obj) {
		if(obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		}else {
			String jsonPayload=TestUtil.getserializedJSON(obj);
			request.body(jsonPayload);
		}
	}
	
	private static boolean setBaseURI(String baseURI) {

		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct Base URI either it's null or empty/blank...!!!");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println(
					"Some Exceptions got occured while Assigning the BASE URI to the Restassured Request...!!!");
			return false;
		}
	}
	
	private static RequestSpecification createRequest(String contentType, Map<String, String> token, Map<String, String> queryParamMap,
			boolean log ) {
		
		RequestSpecification request=null;
		if(log) {
			request=RestAssured.given().log().all();
		}
		else {
			request=RestAssured.given();
		}
		
		//Passing the token
		if(token.size()>0) {
			//request.header("Authorization", "Bearer "+token);
			request.headers(token);
		}
		
		//Passing the Query Parameters in the request
		if(!(queryParamMap == null)) {
			request.queryParams(queryParamMap);
		}
		
		if(contentType != null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}
			else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}
			else if(contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			}
			else if(contentType.equalsIgnoreCase("multipart")) {
				request.multiPart("image", new File("D:\\Scala Assignment\\String-3\\StringConverstion in LowerCase-Output.png"));
			}
		}		
		return request;
	}
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String basePath) {
		return executeAPI(httpMethod,request,basePath);
	}
	
	private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath) {
		
		Response response=null;
		switch (httpMethod) {
		case "GET":
			response=request.get(basePath);
			break;
		case "POST":
			response=request.post(basePath);
			break;
		case "PUT":
			response=request.put(basePath);
			break;
		case "DELETE":
			response=request.delete(basePath);
			break;		

		default:
			break;
		}
		return response;
	}

}
