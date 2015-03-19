package com.epam.cleancode.torpedo.shooter;

import com.epam.cleancode.torpedo.util.Position;

public interface Shooter {

	public void lastShotMissed();

	public void lastShotHit();

	public void lastShotSunkShip();

	public Position shoot();
}
