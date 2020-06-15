package com.messenger.validators;

import java.util.Arrays;
import java.util.Random;

import com.messenger.logs.MessengerLogger;

public class OTPGenerator {
	
	public static String generateOTP(int len) 
    { 
        MessengerLogger.logInfo( "Generating OTP using random() : "); 
  
        // Using numeric values 
        String numbers = "0123456789"; 
  
        // Using random method 
        Random rndm_method = new Random(); 
  
        String otp = new String(); 
  
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            otp = otp+numbers.charAt(rndm_method.nextInt(numbers.length())); 
             
        } 
        return otp;
    } 

}
