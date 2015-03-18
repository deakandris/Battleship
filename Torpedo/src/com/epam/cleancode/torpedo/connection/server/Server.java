package com.epam.cleancode.torpedo.connection.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.cleancode.torpedo.Game;
import com.epam.cleancode.torpedo.connection.TestShit;

public class Server {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		/*
		 * if (args.length != 1) { System.err.println("Usage: java KnockKnockServer <port number>");
		 * System.exit(1); }
		 */
		// int portNumber = Integer.parseInt(args[0]);
		int portNumber = 36512;
		int fieldWidth = 100;
		int fieldHeight = 100;
		String welcomeMsg = "HELLO " + fieldWidth + " " + fieldHeight;

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			TorpedoServer.startServer(new Game(fieldWidth, fieldHeight), welcomeMsg, out, in);

		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port " + portNumber
					+ " or listening for a connection");
			System.out.println(e.getMessage());
		}
		System.out.println("Runtime: " + (System.currentTimeMillis() - startTime) / 1000 + " sec");
		TestShit.main(new String[] { "", "" });
		// System.out.println("OVER!!!!!");

	}

}
