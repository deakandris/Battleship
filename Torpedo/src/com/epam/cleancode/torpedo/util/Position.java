package com.epam.cleancode.torpedo.util;

/**
 * Class representing battlefield position with horizontal and vertical coordinates.
 * 
 * @author Andras_Deak
 *
 */
public class Position {

	public int x;
	public int y;

	/**
	 * Creates a new position at the given coordinates.
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 */
	public Position(final int x, final int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns {@code true} if the parameter is adjacent to this position, including the trivial case of equivalence.
	 * @param position to check
	 * @return whether the given position is next to or equal to this position
	 */
	public boolean isAdjacent(Position position) {
		if (position.x <= x+1 && position.x >= x-1 && position.y <= y+1 && position.y >= y-1) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

}
