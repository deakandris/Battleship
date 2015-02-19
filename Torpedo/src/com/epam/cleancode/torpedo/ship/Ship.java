package com.epam.cleancode.torpedo.ship;

import java.util.Collections;
import java.util.List;

import com.epam.cleancode.torpedo.util.Position;

/**
 * A class representing a ship object.
 * 
 * @author Andras_Deak
 *
 */
public class Ship {

	protected List<Position> hull;

	// TODO collision handling

	public Ship(List<Position> hull) {
		this.hull = hull;
	}

	/**
	 * Checks if the ship got hit from a torpedo to the given position.
	 * 
	 * @param position
	 *            the position of the impact
	 * @return whether the ship got hit
	 */
	public boolean isHit(final Position position) {
		for (final Position part : hull) {
			if (part.equals(position)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a read-only {@link List} of {@link Position}-s defining the hull (body) of this ship.
	 * 
	 * @return The hull of the ship as an unmodifiable list of positions.
	 */
	public List<Position> getHull() {
		return Collections.unmodifiableList(hull);
	}

	/**
	 * Returns {@code true} if the parameter {@link Ship} is too close to this ship, meaning there is a direct
	 * contact between the two.
	 * 
	 * @param shipToCheck
	 * @return
	 */
	public boolean isTooClose(Ship shipToCheck) {
		List<Position> hullToCheck = shipToCheck.getHull();
		for (Position thisShipPos : hull) {
			for (Position posToCheck : hullToCheck) {
				if (thisShipPos.isAdjacent(posToCheck))
					return true;
			}
		}
		return false;
	}

	public String toAsciiArt() {
		// TODO
		return null;
	}

	@Override
	public String toString() {
		return "\nShip:\n  hull=" + hull;
	}

}
