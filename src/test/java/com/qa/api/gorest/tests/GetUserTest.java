package com.qa.api.gorest.tests;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;


import io.restassured.response.Response;

public class GetUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath =  "/public-api/users";
	String token = "9f1cb7ee26331573238375893da9fd550a8ab85b360c73b8b68095580758502c";
	
	
	@Test
	public void getAllUserlistAPITest() {
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, null, true);
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
	}
	
	@Test
	public void getAllUserWithQueryParamTest() {
		Map<String,String> params = new HashMap<String,String>();
			
		params.put("name", "John");
		params.put("gender", "male");
		

		Response response = RestClient.doGet("JSON", baseURI, basePath, token, params, true);
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
	}
	
	
	
	
	

}
