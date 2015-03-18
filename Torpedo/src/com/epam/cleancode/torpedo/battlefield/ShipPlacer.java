package com.epam.cleancode.torpedo.battlefield;

import java.util.List;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.FailedShipPlacementException;

public interface ShipPlacer {

	void placeShipsOnBattlefield(List<Ship> ships, Battlefield field) throws FailedShipPlacementException;
	
}
