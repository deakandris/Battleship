package com.epam.cleancode.torpedo;

import java.util.ArrayList;
import java.util.List;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.Position;

public class Battlefield {

	private final List<Ship> ships;
	private final int width;
	private final int height;

	/**
	 * Create a new battlefield with the given parameters.
	 * 
	 * @param width
	 *            the horizontal length of the field
	 * @param height
	 *            the vertical length of the field
	 * @throws IllegalArgumentException when arguments are lesser or equal to 0.
	 */
	public Battlefield(int width, int height) throws IllegalArgumentException {
		if (width > 0 && height > 0) {
			this.width = width;
			this.height = height;
			ships = new ArrayList<Ship>();
		} else {
			throw new IllegalArgumentException("Arguments must be greater than 0.");
		}
	}

	/**
	 * Add a {@link Ship} to the battlefield's list
	 * @param ship
	 * @throws IllegalArgumentException
	 */
	public void addShipToField(Ship ship) throws IllegalArgumentException {
		if (isShipInsideBounds(ship)) {
			ships.add(ship);
		} else {
			throw new IllegalArgumentException("Ship is outside of the battlefield's bounds.");
		}
	}

	private boolean isShipInsideBounds(Ship ship) {
		for (Position part : ship.getBody()) {
			if (!isPositionInsideBounds(part)) {
				return false;
			}
		}
		return true;
	}

	private boolean isPositionInsideBounds(Position position) {
		int x = position.getX();
		int y = position.getY();
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return true;
		} else {
			return false;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
