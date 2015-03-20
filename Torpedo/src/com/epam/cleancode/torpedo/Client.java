package com.epam.cleancode.torpedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.application.Application;
import com.epam.cleancode.torpedo.application.ClientApplication;
import com.epam.cleancode.torpedo.connection.MessageParser;
import com.epam.cleancode.torpedo.util.MalformedMessageException;
import com.epam.cleancode.torpedo.util.Position;

public class Client {

	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("Client started");

		if (args.length != 2) {
			System.err.println("Usage: java Client <host name> <port number>");
			System.exit(1);
		}
		
		final String host = args[0];
		final int port = Integer.parseInt(args[1]);
		
		try (Socket socket = new Socket(host, port);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
			LOGGER.info("Trying to connect...");
			socket.setTcpNoDelay(true);
			String welcomeMsg = in.readLine();
			try {
				Position dimension = MessageParser.parsePosition(welcomeMsg);
				Application application = new ClientApplication(dimension.getX(), dimension.getY(), in, out);
				application.run();
			} catch (MalformedMessageException e) {
				out.println("ERROR " + e.getMessage());
			}

		} catch (UnknownHostException e) {
			LOGGER.error("Don't know about host " + host);
			System.exit(1);
		} catch (IOException e) {
			LOGGER.error("Couldn't get I/O for the connection to " + host);
			System.exit(1);
		}
		LOGGER.info("GAME OVER | Runtime: " + (System.currentTimeMillis() - startTime) + "ms");
	}

}
