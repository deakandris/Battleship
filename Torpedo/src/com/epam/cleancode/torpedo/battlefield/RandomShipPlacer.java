package com.epam.cleancode.torpedo.battlefield;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.FailedShipPlacementException;

import static java.lang.Math.random;
import static java.lang.Math.floor;

public class RandomShipPlacer implements ShipPlacer {

	private static Logger LOGGER = LoggerFactory.getLogger(RandomShipPlacer.class);
	private static int MAX_ATTEMPT = 999;

	@Override
	public void placeShipsOnBattlefield(List<Ship> ships, Battlefield field) throws FailedShipPlacementException {
		for (Ship ship : ships) {
			tryAddingShipToField(ship, field, 1);
		}
	}

	private void tryAddingShipToField(Ship ship, Battlefield field, int attempt)
			throws FailedShipPlacementException {
		if (attempt > MAX_ATTEMPT) {
			throw new FailedShipPlacementException("Could not add ship to the field after " + MAX_ATTEMPT
					+ " attempt.\n" + ship + "\n" + field);
		}
		final int width = field.getWidth();
		final int height = field.getHeight();
		final int moveX = (int) floor(random() * width);
		final int moveY = (int) floor(random() * height);
		ship.moveBy(moveX, moveY);
		try {
			field.addShipToField(ship);
			LOGGER.info("Added ship to field at " + attempt + ". attempt.");
		} catch (IllegalArgumentException e) {
			ship.moveBy(-moveX, -moveY);
			tryAddingShipToField(ship, field, ++attempt);
		}
	}
}
