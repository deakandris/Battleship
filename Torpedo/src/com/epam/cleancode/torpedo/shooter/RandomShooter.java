package com.epam.cleancode.torpedo.shooter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.epam.cleancode.torpedo.util.Position;

/**
 * A simple {@link Shooter} implementation with random generated field values.
 * 
 * @author Andras_Deak
 *
 */
public class RandomShooter implements Shooter {

	private final int width;
	private final int height;
	private Position lastShot;
	private final Set<Position> whereNotToShoot = new HashSet<>();
	private final Set<Position> whereToShoot = new HashSet<>();
	private final Set<Position> successfulShots = new HashSet<>();
	private final Set<Position> missedShots = new HashSet<>();

	public RandomShooter(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void lastShotMissed() {
		missedShots.add(lastShot);
	}

	@Override
	public void lastShotHit() {
		successfulShots.add(lastShot);
		List<Position> adjacentPositions = lastShot.getStrictlyAdjacentPositions();
		for (Position position : adjacentPositions) {
			if(position.isInsideBounds(width, height) && !whereNotToShoot.contains(position)) {
				whereToShoot.add(position);
			}
		}
	}

	@Override
	public void lastShotSunkShip() {
		successfulShots.add(lastShot);
		whereToShoot.clear();
	}

	@Override
	public Position shoot() {
		Position toShoot;
		boolean doIKnowWhereToShoot = !whereToShoot.isEmpty();
		if (doIKnowWhereToShoot) {
			toShoot = getPositionFromMemory();
		} else {
			toShoot = getNewPosition();
		}
		lastShot = toShoot;
		whereNotToShoot.add(toShoot);
		return toShoot;
	}

	private Position getPositionFromMemory() {
		Position toShoot;
		Iterator<Position> iterator = whereToShoot.iterator();
		toShoot = iterator.next();
		iterator.remove();
		return toShoot;
	}

	private Position getNewPosition() {
		Position toShoot;
		do {
			toShoot = getRandomPosition();
		} while (!isDiagonal(toShoot) || whereNotToShoot.contains(toShoot));
		return toShoot;
	}

	private Position getRandomPosition() {
		int x = new Random().nextInt(width);
		int y = new Random().nextInt(height);
		Position toShoot = new Position(x, y);
		return toShoot;
	}
	
	private boolean isDiagonal(Position position) {
		return (position.getX() % 2) == (position.getY() % 2);
	}
	
	@Override
	public String toString() {
		char[][] chars = new char[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(successfulShots.contains(new Position(x, y))) {
					chars[y][x] = '▓';
				} else if (missedShots.contains(new Position(x, y))) {
					chars[y][x] = 'X';
				} else {
					chars[y][x] = '.';
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
