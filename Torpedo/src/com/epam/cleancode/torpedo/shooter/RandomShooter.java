package com.epam.cleancode.torpedo.shooter;

import java.util.HashSet;
import java.util.Iterator;
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

	public RandomShooter(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void lastShotMissed() {
	}

	@Override
	public void lastShotHit() {
		whereToShoot.addAll(lastShot.getAdjacentPositions());
	}

	@Override
	public void lastShotSunkShip() {
		whereToShoot.clear();
	}

	@Override
	public Position shoot() {
		Position toShoot;
		if (!whereToShoot.isEmpty()) {
			Iterator<Position> iterator = whereToShoot.iterator();
			toShoot = iterator.next();
			iterator.remove();
		} else {
			do {
				toShoot = getRandomPosition();
			} while (whereNotToShoot.contains(toShoot));
			lastShot = toShoot;
			whereNotToShoot.add(toShoot);
		}
		return toShoot;
	}

	private Position getRandomPosition() {
		int x = new Random().nextInt(width);
		int y = new Random().nextInt(height);
		Position toShoot = new Position(x, y);
		return toShoot;
	}
}
