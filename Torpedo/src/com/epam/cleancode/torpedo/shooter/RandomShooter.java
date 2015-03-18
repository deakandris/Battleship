package com.epam.cleancode.torpedo.shooter;

import java.util.List;
import java.util.Random;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.Position;

/**
 * A simple {@link Shooter} implementation with random generated field values.
 * 
 * @author Andras_Deak
 *
 */
public class RandomShooter implements Shooter {

	private int width;
	private int height;
	private Position lastShot;
	private boolean lastShotWasHit;
	private List<Position> whereNotToShoot;
	private List<Ship> ships;

	public RandomShooter(int width, int height, List<Ship> ships) {
		super();
		this.width = width;
		this.height = height;
		this.ships = ships;
	}

	@Override
	public void lastShotMissed() {
		lastShotWasHit = false;
	}

	@Override
	public void lastShotHit() {
		lastShotWasHit = true;
	}

	@Override
	public void lastShotSunkShip() {
		// TODO implement
		// don't care about it more
		lastShotWasHit = false;
	}

	@Override
	public void setGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object whereToShoot() {
		Position toShoot;
		do {
			int x = new Random().nextInt(width);
			int y = new Random().nextInt(height);
			toShoot = new Position(x, y);
		} while(whereNotToShoot.contains(toShoot));
		lastShot = toShoot;
		whereNotToShoot.add(toShoot);
		return toShoot;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getResultFromEnemyFire(Position enemyShootAt) {
		// TODO Auto-generated method stub
		return null;
	}
}
