package com.epam.cleancode.torpedo.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.cleancode.torpedo.util.MalformedMessageException;

public class ClientApplication extends Application {

	public ClientApplication(int width, int height, BufferedReader in, PrintWriter out) {
		super(width, height, in, out);
	}

	@Override
	protected void startUp() throws IOException, MalformedMessageException {
		fire();
	}

}