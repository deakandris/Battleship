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
	protected List<Position> aura;

	// TODO collision handling

	public Ship(List<Position> body) {
		this.body = body;
		createAura();
	}

	private void createAura() {
		for (Position part : body) {
			// @formatter:off
			aura.add(new Position(part.x + 1,  part.y    ));
			aura.add(new Position(part.x + 1,  part.y + 1));
			aura.add(new Position(part.x    ,  part.y + 1));
			aura.add(new Position(part.x - 1,  part.y + 1));
			aura.add(new Position(part.x - 1,  part.y    ));
			aura.add(new Position(part.x - 1,  part.y - 1));
			aura.add(new Position(part.x    ,  part.y - 1));
			aura.add(new Position(part.x + 1,  part.y - 1));
			// @formatter:on
		}
	}

	/**
	 * Checks if the ship got hit from a torpedo to the given position.
	 * 
	 * @param position
	 *            the position of the impact
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

	public boolean isCollide(Ship checkedShip) {
		for (Position thisShipPos : body) {
			for (Position checkedShipCoord : checkedShip.getBody()) {
				if (thisShipPos.equals(checkedShipCoord))
					return true;
			}
		}
		return false;
	}

}
