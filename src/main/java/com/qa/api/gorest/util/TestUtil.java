package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Kavita Pawar
 * This method is used to covert POJO to String
 * 
 * 
 *
 */

public class TestUtil {
	
	public static String getSerializedJSON(Object objUser) {
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		
		try {
			userJson =mapper.writeValueAsString(objUser);
			System.out.println(userJson);
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userJson;
	}
	

}
