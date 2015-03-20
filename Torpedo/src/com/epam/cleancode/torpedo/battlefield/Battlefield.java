package com.epam.cleancode.torpedo.battlefield;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.cleancode.torpedo.connection.FireResponse;
import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipPart;
import com.epam.cleancode.torpedo.util.FailedShipPlacementException;
import com.epam.cleancode.torpedo.util.Position;

public class Battlefield {

	private final List<Ship> ships;
	private final List<Position> enemyShots;
	private final int width;
	private final int height;
	private final ShipPlacer placer;

	/**
	 * Create a new battlefield with the given parameters.
	 * 
	 * @param width
	 *            the horizontal length of the field
	 * @param height
	 *            the vertical length of the field
	 * @throws IllegalArgumentException
	 *             when arguments are lesser or equal to 0.
	 */
	public Battlefield(int width, int height, ShipPlacer placer) throws IllegalArgumentException {
		if (width > 0 && height > 0) {
			this.width = width;
			this.height = height;
			ships = new ArrayList<>();
			enemyShots = new ArrayList<>();
		} else {
			throw new IllegalArgumentException("Arguments must be greater than 0.");
		}
		this.placer = placer;
	}

	public void generateBattlefield(List<Ship> ships) throws FailedShipPlacementException {
		placer.placeShipsOnBattlefield(ships, this);
	}

	/**
	 * Add a {@link Ship} to the battlefield's list
	 * 
	 * @param ship
	 * @throws IllegalArgumentException
	 */
	void addShipToField(Ship ship) throws IllegalArgumentException {
		if (isShipInsideBounds(ship)) {
			if (!isShipColliding(ship)) {
				ships.add(ship);
			} else {
				throw new IllegalArgumentException("Ship is colliding with an other ship.");
			}
		} else {
			throw new IllegalArgumentException("Ship is outside of the battlefield's bounds.");
		}
	}

	private boolean isShipColliding(Ship ship) {
		for (Ship checkedShip : ships) {
			if (ship.isTooClose(checkedShip)) {
				return true;
			}
		}
		return false;
	}

	private boolean isShipInsideBounds(Ship ship) {
		return ship.isInsideBounds(width, height);
	}

	

	public FireResponse getResultOfEnemyFire(Position enemyShootAt) {
		enemyShots.add(enemyShootAt);
		for (Ship ship : ships) {
			FireResponse shipResponse = ship.haveBeenShotAt(enemyShootAt);
			switch (shipResponse) {
			case HIT:
				return FireResponse.HIT;
			case SUNK:
				if (allShipsAreOut()) {
					return FireResponse.LOST;
				}
				return FireResponse.SUNK;
			default:
			}
		}
		return FireResponse.MISS;
	}

	private boolean allShipsAreOut() {
		for (Ship ship : ships) {
			if (!ship.isDestroyed()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return the read-only list of the ships on the field.
	 * 
	 * @return an {@link java.util.Collections.UnmodifiableList.UnmodifiableList UnmodifiableList} of
	 *         {@link Ship}-s
	 * @see java.util.Collections#unmodifiableList(List)
	 */
	List<Ship> getShips() {
		return Collections.unmodifiableList(ships);
	}

	int getWidth() {
		return width;
	}

	int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		char[][] chars = new char[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(enemyShots.contains(new Position(x, y))) {
					chars[y][x] = 'X';
				} else {
					chars[y][x] = '.';
				}
			}
		}
		for (Ship ship : ships) {
			for (ShipPart part : ship.getHull()) {
				Position position = part.getPosition();
				if (part.isDamaged()) {
					chars[position.getY()][position.getX()] = '▓';
				} else {
					chars[position.getY()][position.getX()] = '▒';
				}
			}
		}
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < height; y++) {
			builder.append(chars[y]);
			builder.append('\n');
		}
		return builder.toString();
	}
}
