package com.epam.cleancode.torpedo.connection.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.cleancode.torpedo.Game;
import com.epam.cleancode.torpedo.connection.MessageParser;
import com.epam.cleancode.torpedo.util.Position;

public class Client {
	private static final String LOCALHOST = "localhost";
	private static final String SAJAT = "10.0.12.227";
	private static final String ZOLI = "10.0.12.229";
	private static final String GABIKA = "10.0.9.15";
	private static final String DANI = "10.0.9.51";
	private static final String GABORL = "10.0.13.55";
	private static final String KRISZTA = "10.0.13.250";
	
	
    public static void main(String[] args){
         
      /*  if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
       	*/
        String hostName = LOCALHOST;
       
        int portNumber = Integer.parseInt("36512");
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
        	System.out.println("Client: Trying to connect...");
        /*	byte[] b = new byte[256];
        	kkSocket.getInputStream().read(b);
        	System.out.println(new String(b));
        	*/
        	kkSocket.setTcpNoDelay(true);
        	Position dimension = MessageParser.parseCoordinate(in.readLine());
        	TorpedoClient.startClient(new Game( dimension.getX(), dimension.getY()), out, in);
        	
          } catch (UnknownHostException e) {
              System.err.println("Don't know about host " + hostName);
              System.exit(1);
          } catch (IOException e) {
              System.err.println("Couldn't get I/O for the connection to " +
                  hostName);
              System.exit(1);
          }
        
        try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // main(args);
          
         // System.out.println("OVER!!!!!");
      }
       
}
