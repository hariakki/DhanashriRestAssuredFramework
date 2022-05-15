package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restClient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class ImgureAPI {
	
	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUserName;
	String refreshToken;
	String baseURL;
	String basePath;

	
	@BeforeMethod
	public void setUp() {
		tokenMap = Token.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUserName = tokenMap.get("account_username").toString();
		refreshToken = tokenMap.get("refresh_token").toString();
		 
	}
	
	@Test
	public void getAccessBlockStatusTest() {
		Map<String, String> authTokenMap = Token.getAuthToken();
		baseURL = "https://api.imgur.com";
		basePath = "/account/v1/"+accountUserName+"/block";		
		Response response = RestClient.doGet(baseURL, null, basePath, authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void getAcountImageTest() {
		Map<String, String> authTokenMap = Token.getAuthToken();
		baseURL = "https://api.imgur.com";
		basePath = "/3/account/"+accountUserName+"/images";		
		Response response = RestClient.doGet(baseURL, null, basePath, authTokenMap, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	
	@Test
	public void postUploadImageTest() {
		Map<String, String> clientIDMap = Token.getClientID();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "ImageUpload");
		formMap.put("description", "ImageUploadTest");
		
		Response response =RestClient.doPost("https://api.imgur.com", "multipart", "/3/image", clientIDMap, null, true, formMap);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		
	}
	

}
