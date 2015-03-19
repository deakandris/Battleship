package com.epam.cleancode.torpedo.util;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Immutable class representing general position with horizontal and vertical coordinates.
 * 
 * @author Andras_Deak
 *
 */
public class Position {

	private final int x;
	private final int y;

	/**
	 * Creates a new position at the given coordinates.
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns {@code true} if the parameter is adjacent to this position, including the trivial case of
	 * equivalence.
	 * 
	 * @param position
	 *            to check
	 * @return whether the given position is next to or equal to this position
	 */
	public boolean isAdjacent(final Position position) {
		if (abs(x - position.x) <= 1 && abs(y - position.y) <= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a {@code List} of {@code Position}-s adjacent to this {@code Position}. Adjacent means that
	 * the difference between their coordinates are maximum 1, excluding the trivial case of equivalence.
	 * 
	 * @return {@link List} containing the adjacent {@link Position}-s
	 */
	public List<Position> getAdjacentPositions() {
		List<Position> result = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				result.add(new Position(x + i, y + j));
			}
		}
		return result;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
	public boolean equals(final Object obj) {
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
		return x + " " + y;
	}

}
