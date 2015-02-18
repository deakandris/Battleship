package com.epam.cleancode.torpedo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ShipsFileReader {

	public static void buildShipsFromFile(String file) {
		Path path = FileSystems.getDefault().getPath(file);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        int shift = line.indexOf('x');
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
