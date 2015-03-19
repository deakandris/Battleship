package com.epam.cleancode.torpedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.application.Application;
import com.epam.cleancode.torpedo.application.ServerApplication;
import com.epam.cleancode.torpedo.connection.Constants;

public class Server {

	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	private static final int FIELD_WIDTH = 100;
	private static final int FIELD_HEIGHT = 100;

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		LOGGER.info("Server started");
		/*
		 * if (args.length != 1) { System.err.println("Usage: java KnockKnockServer <port number>");
		 * System.exit(1); }
		 */
		// int portNumber = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(Constants.PORT);
				Socket clientSocket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);) {

			Application application = new ServerApplication(FIELD_WIDTH, FIELD_HEIGHT, in, out);
			application.run();

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			System.exit(1);
		}
		LOGGER.info("GAME OVER | Runtime: " + (System.currentTimeMillis() - startTime) / 1000 + " sec");

	}

}
