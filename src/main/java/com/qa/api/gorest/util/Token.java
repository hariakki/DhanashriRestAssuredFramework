package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Token {
	
	public static Map<Object, Object> aapTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientID = "b2f1cdd82cd54f4";
	
	public static Map<Object, Object> getAccessToken() {
		
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "0f6c16a0e98732320df36f3cb88fe1d0f8574a66");
		formParams.put("client_id", "b2f1cdd82cd54f4");
		formParams.put("client_secret", "34eea9b26a480295a1b6c85039aea020417fc804");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJson = given().
			formParams(formParams).
				when().
					post("https://api.imgur.com/oauth2/token").
						then().
							extract().
								jsonPath();
		
		System.out.println(tokenJson.getMap(""));
		System.out.println(tokenJson.prettyPrint());
		
		aapTokenMap=tokenJson.getMap("");
		return aapTokenMap;
	}
	
	public static Map<String, String> getAuthToken() {
		String authToken = aapTokenMap.get("access_token").toString();
		System.out.println("Authorization Token---->" + authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
	}
	
	public static Map<String, String> getClientID() {
		System.out.println("Cliend Id is ---->" + clientID);
		tokenMap.put("Authorization", "Client-ID "+clientID);
		return tokenMap;
	}

}
