package com.util;


public class ResponseUtils {
	public static String combineResponse(String username, String type, String response) {
		String result = "{\"username\":\""+username+"\",\"type\":\""+type+"\",\"response\":"+response+"}";
        return result;
    }
}
