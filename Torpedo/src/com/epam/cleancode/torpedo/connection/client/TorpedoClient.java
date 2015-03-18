package com.epam.cleancode.torpedo.connection.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.cleancode.torpedo.Game;

public class TorpedoClient {
	
	
	public static void startClient(Game communicationOfficer, PrintWriter clientOutPut, BufferedReader clientInPut) throws IOException {
		//TorpedoMainFrame debugWindow = new TorpedoMainFrame("Client");  
		//lövök           
		clientOutPut.println(communicationOfficer.askForFireCoordinates());      
          while (!communicationOfficer.isOver() ) {
          	//lövésem eredménye
        	  communicationOfficer.decodeMyFireResult(clientInPut.readLine());
          	if(!communicationOfficer.isOver()){
	            	//lõ rám
	            	// vissza adom az õ lövésének eredményét
          		clientOutPut.println(communicationOfficer.checkFireResult(clientInPut.readLine()));
     //     		communicationOfficer.drawField(debugWindow);
	            	// én is lövök
          		clientOutPut.println(communicationOfficer.askForFireCoordinates());
          	}
          }
	}

}