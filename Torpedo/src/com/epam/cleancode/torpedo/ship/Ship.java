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
public abstract class Ship {

	protected List<Position> body;

	public Ship(List<Position> body) {
		this.body = body;
		
	}

	/**
	 * Checks if the ship got hit from a torpedo to the given position.
	 * @param position the position of the impact
	 * @return whether the ship got hit
	 */
	public boolean isHit(final Position position) {
		for (final Position part : body) {
			if (part.equals(position)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return The body of the ship as an unmodifiable list of {@link Position}-s.
	 */
	public List<Position> getBody() {
		return Collections.unmodifiableList(body);
	}

}
