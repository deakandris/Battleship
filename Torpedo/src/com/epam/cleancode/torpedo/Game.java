package com.epam.cleancode.torpedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.ShipBuilder;
import com.epam.cleancode.torpedo.util.ShipsFileReader;

public class Game {

	public static void main(String[] args) {
		// TODO connect ShipsFileReader with BattleField
		try {
			BufferedReader reader = ShipsFileReader.createReader("ships.txt");
			List<Ship> ships = ShipBuilder.buildShips(reader);
			System.out.println(ships);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* TODO somehow correct Ship Positions from FileInput dimensions (4 x 4) to BattleField dimensions (n x m)
				this might mean that the Position class can no longer be immutable, or the Ship's body will need full rework when placing on BattleField
		*/			
		
		// TODO create BattleField
	}

}
