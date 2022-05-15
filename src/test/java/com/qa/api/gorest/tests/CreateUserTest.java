package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restClient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI="https://gorest.co.in";
	String basePath ="/public-api/users";
	String token="201ad6d68b48c30263da05f34882fdb5a025aca392169ae4a36140fb809fb361";
	
	@DataProvider
	//return type of @DataProvider is two dimensional object array i.e. Object[][]
	public Object[][] getUserData()
	{
		Object userData[][] = ExcelUtil.getTestData("userdata");
		return userData;
	}
	
	@Test(dataProvider = "getUserData")  //using this-->(dataProvider = "getUserData"), established connection between @Test & @DataProvider
	public void createUserAPIPOSTTest(String name, String email, String gender, String status) {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);
		
		//User user= new User("Smriti Khanna", "SmritiKhanna@gmail.com","Female","active");
		User user= new User(name, email, gender, status);
		Response response=RestClient.doPost(baseURI, "JSON", basePath, authTokenMap, null, true, user);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());			
		System.out.println("______________________________________________");
	}

}
