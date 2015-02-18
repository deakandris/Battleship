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

public class ShipsFileReader {

	public static List<Ship> buildShipsFromFile(String file) throws IOException {
		Path path = FileSystems.getDefault().getPath(file);
		List<Position> shipPosition = new ArrayList<>();
		Position position;
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.indexOf('x') != -1) {
				// TODO think of a way to detect collision checking
			}
		}
	}
}
