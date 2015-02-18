package com.epam.cleancode.torpedo;

import com.epam.cleancode.torpedo.util.ShipsFileReader;

public class Game {

	public static void main(String[] args) {
		ShipsFileReader.buildShipsFromFile("ships.txt");
	}

}
