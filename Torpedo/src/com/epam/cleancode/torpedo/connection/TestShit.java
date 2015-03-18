package com.epam.cleancode.torpedo.connection;

import com.epam.cleancode.torpedo.connection.client.Client;
import com.epam.cleancode.torpedo.connection.server.Server;


public class TestShit {
	
	

	public static void main(final String[] args) {
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				Server.main(args);
				
			}
		}).start();
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				Client.main(args);
				
			}
		}).start();

	}
	
	

}
