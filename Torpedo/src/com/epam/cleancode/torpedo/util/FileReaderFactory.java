package com.epam.cleancode.torpedo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderFactory {

	public static BufferedReader createReader(String file) throws IOException {
		Path path = FileSystems.getDefault().getPath(file);
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		return reader;
	}
	
}
