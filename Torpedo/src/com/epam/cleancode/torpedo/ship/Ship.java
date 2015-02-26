package com.epam.cleancode.torpedo.ship;

import java.util.ArrayList;
import java.util.Arrays;
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

	private final List<Position> hull;
	private int numberOfHits;

	public Ship(Position... hull) {
		this.hull = Arrays.asList(hull);
	}

	public Ship(List<Position> hull) {
		this.hull = hull;
	}

	public Ship(Ship ship) {
		hull = new ArrayList<>(ship.getHull());
	}

	/**
	 * Checks if the ship got hit from a torpedo to the given position.
	 * 
	 * @param position
	 *            the position of the impact
	 * @return whether the ship got hit
	 */
	public boolean isHit(final Position position) {
		if (hull.contains(position)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns {@code true} if the parameter {@link Ship} is too close to this ship, meaning there is a direct
	 * contact between the two.
	 * 
	 * @param shipToCheck
	 * @return
	 */
	public boolean isTooClose(final Ship shipToCheck) {
		List<Position> hullToCheck = shipToCheck.getHull();
		for (Position thisShipPos : hull) {
			for (Position posToCheck : hullToCheck) {
				if (thisShipPos.isAdjacent(posToCheck))
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
	
	public int getNumberOfHits() {
		return numberOfHits;
	}

	public void increaseNumberOfHits() {
		numberOfHits++;
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
