package com.qa.api.gorest.restclient;

import java.util.Map;

import javax.sound.midi.Soundbank;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * This class is having all http methods which will call the apis and having generic methods for getting the
 *  response and fetch the values from response
 *  @author : Kavita Pawar
 */

public class RestClient {
	
	// HTTP Methods: GET POST PUT DELETE
	
	public static Response doGet(String contentType, String baseURI, String basePath, String token, 
			Map<String,String> paramsmap, boolean log)
	{
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType,token,paramsmap,log);
			return getResponse("GET", request, basePath);
		}
		return null;	
	}
	
	public static Response doPost(String contentType, String baseURI, String basePath, String token, 
			Map<String,String> paramsmap, boolean log, Object obj)
	{
		if (setBaseURI(baseURI)) {
			RequestSpecification request = createRequest(contentType,token,paramsmap,log);
			String jsonPayLoad = TestUtil.getSerializedJSON(obj);
			request.body(jsonPayLoad);
			return getResponse("POST", request, basePath);
		}
		return null;	
	}
	
	private  static boolean setBaseURI(String baseURI) {
		
		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass the correct base URI ....");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		}
		catch(Exception e) {
			System.out.println("some eception got occured while assigning the base URL with Resst Assured");
			return false;
		}
		
	}
	
	private static RequestSpecification createRequest(String contentType, String token, Map<String,String> paramsmap, boolean log) {
		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		}
		else {
			request = RestAssured.given();
		}
		
		if (token!=null) {
			request.header("Authorization" , "Bearer "+token);			 
		}
		
		if (!(paramsmap == null)) {
			request.queryParams(paramsmap);
			
		}
		
		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		}else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		}else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}
		
		return request;
		
	}
	
	private static Response getResponse(String httpMethods, RequestSpecification request, String basePath) {
		
		return executAPI(httpMethods, request, basePath);
	
	}
	
	private static Response executAPI(String httpMethods, RequestSpecification request, String basePath) {
		Response response = null;
		 switch (httpMethods) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please pass the corect HTTP methodsW");
			break;
		}
		return response;
	}

}
