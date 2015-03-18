package com.epam.cleancode.torpedo.connection;

import com.epam.cleancode.torpedo.util.MalformedMessageException;
import com.epam.cleancode.torpedo.util.Position;

public class MessageParser {

	public static Position parseCoordinate(String message) {
		String[] parts = message.split(" ");
		if (parts.length == 3) {
			int width = Integer.parseInt(parts[1]);
			int height = Integer.parseInt(parts[2]);
			return new Position(width, height);
		} else {
			throw new MalformedMessageException("Cannot parse coordinates from message.");
		}

	}
}
