package com.messenger.validators;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SMSHandler {

	
public String sendSMS(String message) {
	String apiKey = null;
	String apiUrl = null;
	URL url = null;
	URLConnection urlConnection = null;
	BufferedReader bufferReader = null;
	String line = "";
	StringBuffer buffer = null;
	try {
		buffer = new StringBuffer();
		apiKey = "apiKey=kLDlQg74mOo-cElSGthAczD1JRzi1kZStISuKCUC1v";
		apiUrl = "https://api.textlocal.in/send/?"+apiKey+message;
		url = new URL(apiUrl);
		urlConnection = url.openConnection();
		urlConnection.setDoOutput(true);
		bufferReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		while((line = bufferReader.readLine())!=null) {
			buffer.append(line).append("\n");
		}
		System.out.println("SMS Response -- "+buffer.toString());
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return buffer.toString();
	
}	
	
	
}
