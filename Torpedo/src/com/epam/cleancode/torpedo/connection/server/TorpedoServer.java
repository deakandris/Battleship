package com.epam.cleancode.torpedo.connection.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.cleancode.torpedo.Game;

public class TorpedoServer {
   
    public static void startServer(final Game commOfficer,String welcomeMsg, PrintWriter serverOutPut, BufferedReader serverInPut  ) throws IOException{
    //	TorpedoMainFrame debugWindow = new TorpedoMainFrame("Server");
    	//System.out.println("Server is ready");
        serverOutPut.println(welcomeMsg );
      //  System.out.println("Server sent hello");
        while (!commOfficer.isOver()) {
        	//vissza adom kliens l�v�s�nek eredm�ny�t
        	serverOutPut.println(commOfficer.checkFireResult(serverInPut.readLine()));          	       	
        	if(!commOfficer.isOver()){
        		//l�v�k
	        	serverOutPut.println(commOfficer.askForFireCoordinates());  
	        	//v�rok a l�v�sem eredm�ny�re
	        	commOfficer.decodeMyFireResult(serverInPut.readLine());
	//        	commOfficer.drawField(debugWindow);
	        }
        	
        } 	
    }
    
}
