package com.epam.cleancode.torpedo.ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ShipParser {
	public static List<Ship> parseShips(BufferedReader reader) throws IOException {
		List<Ship> result = new ArrayList<>();
		List<ShipPart> shipHull = new ArrayList<>();
		String line = null;
		int lineIndexInShip = 0;
		int lineIndex = 0;
		while ((line = reader.readLine()) != null) {
			if (isShipLine(line)) {
				List<Integer> shipPartIndices = parseShipLine(line);
				List<ShipPart> shipPartsInOneLine = createShipPartsInOneLine(shipPartIndices, lineIndexInShip);
				shipHull.addAll(shipPartsInOneLine);
				lineIndexInShip++;
			} else if (isQuantityLine(line)) {
				if (!shipHull.isEmpty()) {
					int quantity = parseInt(line);
					List<Ship> ships = createShipsFromCommonHull(shipHull, quantity);
					result.addAll(ships);
				} else {
					throw new IOException("Syntax error in ships file: ships must be at least 1 unit long.");
				}
				lineIndexInShip = 0;
				shipHull.clear();
			} else {
				throw new IOException("Syntax error in ships file: unidentified character at line " + lineIndex + ".");
			}
			lineIndex++;
		}
		return result;
	}
	
	private static boolean isShipLine(final String line) {
		return line.matches("[\\.x]+");
	}
	
	private static List<Integer> parseShipLine(final String line) {
		List<Integer> result = new ArrayList<>();
		for (int charIndex = 0; charIndex < line.length(); charIndex++) {
			if (line.charAt(charIndex) == 'x') {
				result.add(charIndex);
			}
		}
		return result;
	}
	
	private static List<ShipPart> createShipPartsInOneLine(final List<Integer> shipPartIndices, final int lineIndex) {
		List<ShipPart> result = new ArrayList<>();
		for(Integer index : shipPartIndices) {
			result.add(new ShipPart(index, lineIndex));
		}
		return result;
	}
	
	private static boolean isQuantityLine(String line) {
		return line.matches("[0-9]+");
	}
	
	private static List<Ship> createShipsFromCommonHull(final List<ShipPart> hull, final int quantity) {
		List<Ship> result = new ArrayList<>();
		for (int i = 0; i < quantity; i++) {
			List<ShipPart> cloneHull = new ArrayList<>();
			for (ShipPart part : hull) {
				cloneHull.add(part.clone());
			}
			result.add(new Ship(cloneHull));
		}
		return result;
	}
}
