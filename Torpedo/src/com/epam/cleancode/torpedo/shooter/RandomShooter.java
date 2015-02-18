package com.epam.cleancode.torpedo.shooter;

import com.epam.cleancode.torpedo.Battlefield;
import com.epam.cleancode.torpedo.util.Position;

/**
 * A simple {@link Shooter} implementation with random generated field values.
 * @author Andras_Deak
 *
 */
public class RandomShooter implements Shooter {

	private Battlefield field;
	
	public RandomShooter(Battlefield field) {
		this.field = field;
	}

	/* (non-Javadoc)
	 * @see com.epam.cleancode.torpedo.Shooter#shoot(int, int)
	 */
	@Override
	public boolean shoot(Position position) {
		return field.getValueAt(position);
	}

	public void shootUntilAllShipsAreOut(){
		// TODO
	}

}
