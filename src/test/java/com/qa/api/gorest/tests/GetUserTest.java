package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restClient.RestClient;

import io.restassured.response.Response;

public class GetUserTest {
	
	String baseURI="https://gorest.co.in";
	String basePath ="/public-api/users";
	String token="201ad6d68b48c30263da05f34882fdb5a025aca392169ae4a36140fb809fb361";
	
	@Test(priority = 1)
	public void getAllUserListAPITest()
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		Response response=RestClient.doGet(baseURI, "JSON", basePath, authTokenMap, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test(priority = 2)
	public void getUserWithQueryParamAPITest()
	{
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		
		Map<String, String> params= new HashMap<String, String>();
		params.put("name", "Preity");
		params.put("gender", "female"); 
		
		Response response=RestClient.doGet(baseURI, "JSON", basePath, authTokenMap, params, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());	
	}

}
