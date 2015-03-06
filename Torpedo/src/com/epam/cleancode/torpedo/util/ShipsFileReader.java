package com.epam.cleancode.torpedo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipPart;

public class ShipsFileReader {

	public static List<Ship> buildShipsFromFile(String file) throws IOException {
		BufferedReader reader = createReader(file);
		return buildShips(reader);
	}

	private static BufferedReader createReader(String file) throws IOException {
		Path path = FileSystems.getDefault().getPath(file);
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		return reader;
	}
	private static List<Ship> buildShips(BufferedReader reader) throws IOException {
		List<Ship> result = new ArrayList<>();
		List<ShipPart> shipHull = new ArrayList<>();
		String line = null;
		int lineIndexInShip = 0;
		int lineIndex = 0;
		while ((line = reader.readLine()) != null) {
			if (line.matches("[\\.x]+")) {
				for (int charIndex = 0; charIndex < line.length(); charIndex++) {
					if (line.charAt(charIndex) == 'x') {
						shipHull.add(new ShipPart(charIndex, lineIndexInShip));
					}
				}
				lineIndexInShip++;
			} else if (line.matches("[0-9]+")) {
				if (!shipHull.isEmpty()) {
					Integer quantity = Integer.parseInt(line);
					for(int i = 0; i < quantity; i++) {
						result.add(new Ship(shipHull));
					}
					shipHull = new ArrayList<>();
				} else {
					throw new IOException("Syntax error in ships file: ships must be at least 1 unit long.");
				}
				lineIndexInShip = 0;
			} else {
				throw new IOException("Syntax error in ships file: unidentified character at line " + lineIndex + ".");
			}
			lineIndex++;
		}
		return result;
	}
}
