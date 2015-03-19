package com.epam.cleancode.torpedo.application;

import java.io.BufferedReader;
import java.io.PrintWriter;

public final class ServerApplication extends Application {

	private final int width;
	private final int height;

	public ServerApplication(final int width, final int height, BufferedReader in, PrintWriter out) {
		super(width, height, in, out);
		this.width = width;
		this.height = height;
	}

	@Override
	protected void startUp() {
		out.println("HELLO " + width + " " + height);
	}

}
