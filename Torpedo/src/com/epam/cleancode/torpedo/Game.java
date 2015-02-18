package com.epam.cleancode.torpedo;

import java.io.IOException;

import com.epam.cleancode.torpedo.util.ShipsFileReader;

public class Game {

	public static void main(String[] args) {
		// TODO connect ShipsFileReader with BattleField
		try {
			ShipsFileReader.buildShipsFromFile("ships.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* TODO somehow correct Ship Positions from FileInput dimensions (4 x 4) to BattleField dimensions (n x m)
				this might mean that the Position class can no longer be immutable, or the Ship's body will need full rework when placing on BattleField
		*/			
		
		// TODO create BattleField
	}

}
