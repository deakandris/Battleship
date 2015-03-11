package com.epam.cleancode.torpedo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.shipplacer.RandomShipPlacer;
import com.epam.cleancode.torpedo.shipplacer.ShipPlacer;
import com.epam.cleancode.torpedo.util.FailedShipPlacementException;
import com.epam.cleancode.torpedo.util.ShipBuilder;
import com.epam.cleancode.torpedo.util.ShipsFileReader;

public class Game {

	public static Logger LOGGER = LoggerFactory.getLogger(Game.class);
	
	public static void main(String[] args) {
		// TODO connect ShipsFileReader with BattleField
		try {
			BufferedReader reader = ShipsFileReader.createReader("ships.txt");
			List<Ship> ships = ShipBuilder.buildShips(reader);
			LOGGER.info(ships.toString());
			Battlefield field = new Battlefield(10, 10);
			ShipPlacer placer = new RandomShipPlacer();
			placer.placeShipsOnBattlefield(ships, field);
			LOGGER.info("Initial battlefield:\n" + field);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FailedShipPlacementException e) {
			LOGGER.error("Ship placement failed!", e);
		}
		
		/* TODO somehow correct Ship Positions from FileInput dimensions (4 x 4) to BattleField dimensions (n x m)
				this might mean that the Position class can no longer be immutable, or the Ship's body will need full rework when placing on BattleField
		*/			
		
		// TODO create BattleField
	}

}
