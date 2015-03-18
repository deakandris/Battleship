package com.epam.cleancode.torpedo.connection.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.cleancode.torpedo.Game;

public class TorpedoClient {
	
	
	public static void startClient(Game communicationOfficer, PrintWriter clientOutPut, BufferedReader clientInPut) throws IOException {
		//TorpedoMainFrame debugWindow = new TorpedoMainFrame("Client");  
		//l�v�k           
		clientOutPut.println(communicationOfficer.askForFireCoordinates());      
          while (!communicationOfficer.isOver() ) {
          	//l�v�sem eredm�nye
        	  communicationOfficer.decodeMyFireResult(clientInPut.readLine());
          	if(!communicationOfficer.isOver()){
	            	//l� r�m
	            	// vissza adom az � l�v�s�nek eredm�ny�t
          		clientOutPut.println(communicationOfficer.checkFireResult(clientInPut.readLine()));
     //     		communicationOfficer.drawField(debugWindow);
	            	// �n is l�v�k
          		clientOutPut.println(communicationOfficer.askForFireCoordinates());
          	}
          }
	}

}