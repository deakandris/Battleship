package com.epam.cleancode.torpedo;

import com.epam.cleancode.torpedo.battlefield.Battlefield;
import com.epam.cleancode.torpedo.battlefield.RandomShipPlacer;
import com.epam.cleancode.torpedo.connection.FireResponseMessage;
import com.epam.cleancode.torpedo.connection.MessageParser;
import com.epam.cleancode.torpedo.shooter.RandomShooter;
import com.epam.cleancode.torpedo.shooter.Shooter;
import com.epam.cleancode.torpedo.util.Position;

//import com.epam.cleancode.torpedo.utility.ui.TorpedoMainFrame;

public class Game {

	private Shooter shooter;
	private Battlefield field;

	public Game(int width, int height) {
		shooter = new RandomShooter(width, height);
		field = new Battlefield(width, height, new RandomShipPlacer());
	}

	public void decodeMyFireResult(String message) {
		switch (FireResponseMessage.valueOf(message)) {
		case MISS:
			shooter.lastShotMissed();
			break;
		case HIT:
			shooter.lastShotHit();
			break;
		case SUNK:
			shooter.lastShotSunkShip();
			break;
		case LOST:
			shooter.setGameOver();
			break;
		}
	}

	public String checkFireResult(String message) {
		try {
			// System.out.println("Enemy shot at: " + message);
			Position enemyShootAt = MessageParser.parseCoordinate(message);
			// return "MISS";
			return shooter.getResultFromEnemyFire(enemyShootAt);
		} catch (IllegalArgumentException e) {
			return "DON'T_CHEAT";
		}

	}

	public String askForFireCoordinates() {
		String result = shooter.whereToShoot().toString();
		// System.out.println("I shot at: " + result);
		if (result.equals(FireResponseMessage.HIT)) {
			// System.out.println("I got a targer");
		}
		return "FIRE " + result;
	}

	public boolean isOver() {
		return shooter.isGameOver();
	}
}
