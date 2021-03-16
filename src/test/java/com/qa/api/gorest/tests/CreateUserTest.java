package com.qa.api.gorest.tests;




import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath =  "/public-api/users";
	String token = "9f1cb7ee26331573238375893da9fd550a8ab85b360c73b8b68095580758502c";

	/* @Test
	public void postUserAPITest() {
		
		User objUser = new User("Tani1sdsd", "Female", "kdfjdjg@sdk.com", "Active");
		
		Response response = RestClient.doPost("JSON", baseURI, basePath, token, null, true,objUser);
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));		
	}*/

	  @DataProvider
	  public Object[][] getUserData() {
		 Object userData[][]=  ExcelUtil.getTestData("userData");
		 System.out.println("4");
		 return userData;
	  }
	  
	  @Test(dataProvider = "getUserData")
		public void postUserAPITest(String Name, String Gender, String Email, String Status) {
		  System.out.println("5");
		User objUser =  new User(Name,Gender,Email,Status);
		  
		Response response = RestClient.doPost("JSON", baseURI, basePath, token, null, true,objUser);
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));		
	}
	
	
}
