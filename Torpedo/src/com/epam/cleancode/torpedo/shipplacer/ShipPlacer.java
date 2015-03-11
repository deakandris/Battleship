package com.epam.cleancode.torpedo.shipplacer;

import java.util.List;

import com.epam.cleancode.torpedo.Battlefield;
import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.FailedShipPlacementException;

public interface ShipPlacer {

	void placeShipsOnBattlefield(List<Ship> ships, Battlefield field) throws FailedShipPlacementException;
	
}
