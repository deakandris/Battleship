package com.epam.cleancode.torpedo.ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epam.cleancode.torpedo.connection.FireResponse;
import com.epam.cleancode.torpedo.util.Position;

/**
 * A class representing a ship object.
 * 
 * @author Andras_Deak
 *
 */
public class Ship {

	private final List<ShipPart> hull;

	public Ship(ShipPart... hull) {
		this.hull = Arrays.asList(hull);
	}

	public Ship(List<ShipPart> hull) {
		this.hull = new ArrayList<>(hull);
	}

	public Ship(Ship ship) {
		hull = new ArrayList<>(ship.getHull());
	}

	/**
	 * Checks whether the ship have been shot at the given position and if true, damages the corresponding part
	 * of the ship.
	 * 
	 * @param position
	 *            the position of the shot
	 * @return whether the ship got hit
	 */
	public FireResponse haveBeenShotAt(final Position position) {
		for (ShipPart part : hull) {
			if (position.equals(part.getPosition())) {
				part.setDamaged();
				if (isDestroyed()) {
					return FireResponse.SUNK;
				}
				return FireResponse.HIT;
			}
		}
		return FireResponse.MISS;
	}

	public boolean isDestroyed() {
		for (ShipPart part : hull) {
			if (!part.isDamaged()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns {@code true} if the parameter {@link Ship} is too close to this ship, meaning there is a direct
	 * contact between the two.
	 * 
	 * @param shipToCheck
	 * @return
	 */
	public boolean isTooClose(final Ship shipToCheck) {
		List<ShipPart> hullToCheck = shipToCheck.getHull();
		for (ShipPart thisShipPart : hull) {
			for (ShipPart partToCheck : hullToCheck) {
				if (thisShipPart.isAdjacent(partToCheck))
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns {@code true} if the ship is inside the bounding box defined by the origo and the parameters as max
	 * values along axes.
	 * 
	 * @param width
	 *            the maximum value along the x axis
	 * @param height
	 *            the maximum value along the y axis
	 * @return whether the ship is inside the given bounds
	 * @see ShipPart#isInsideBounds(int, int)
	 * @see Position#isInsideBounds(int, int)
	 */
	public boolean isInsideBounds(final int width, final int height) {
		for (ShipPart part : hull) {
			if (!part.isInsideBounds(width, height)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Move the ship by a fixed amount along both axes
	 * 
	 * @param x
	 *            horizontal offset
	 * @param y
	 *            vertical offset
	 * @see ShipPart#moveBy(int, int)
	 */
	public void moveBy(final int x, final int y) {
		for (ShipPart part : hull) {
			part.moveBy(x, y);
		}
	}

	/**
	 * Returns a read-only {@link List} of {@link ShipPart}-s defining the hull (body) of this ship.
	 * 
	 * @return The hull of the ship as an unmodifiable list of positions.
	 */
	public List<ShipPart> getHull() {
		return Collections.unmodifiableList(hull);
	}

	/**
	 * Returns the number of hits the ship has suffered.
	 * 
	 * @return number of hits
	 */
	public int getNumberOfHits() {
		int result = 0;
		for (ShipPart part : hull) {
			if (part.isDamaged()) {
				result++;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Ship:" + hull;
	}

}
