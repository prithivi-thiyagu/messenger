package com.messenger.logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MessengerLogger {

static PrintStream stream = null;
	
public static void logInfo(String message) {
	try {
		if(stream == null) {
		File file = new File("logs.txt");
		stream = new PrintStream(file);
		System.out.println("Log File Path ------->"+file.getAbsolutePath());
		}
		stream.append(message).println();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}	

}
