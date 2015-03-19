package com.epam.cleancode.torpedo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.battlefield.Battlefield;
import com.epam.cleancode.torpedo.battlefield.RandomShipPlacer;
import com.epam.cleancode.torpedo.connection.FireResponse;
import com.epam.cleancode.torpedo.connection.MessageParser;
import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.shooter.RandomShooter;
import com.epam.cleancode.torpedo.shooter.Shooter;
import com.epam.cleancode.torpedo.util.MalformedMessageException;
import com.epam.cleancode.torpedo.util.Position;

//import com.epam.cleancode.torpedo.utility.ui.TorpedoMainFrame;

public class Game {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

	private boolean gameOver;
	private Shooter shooter;
	private Battlefield field;

	public Game(int width, int height, List<Ship> ships) {
		shooter = new RandomShooter(width, height);
		field = new Battlefield(width, height, new RandomShipPlacer());
		field.generateBattlefield(ships);
		LOGGER.info("Field generated\n" + field);
	}

	public void processMyFireResult(String message) throws MalformedMessageException {
		try {
			switch (FireResponse.valueOf(message)) {
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
				gameOver = true;
				break;
			}
		} catch (IllegalArgumentException e) {
			throw new MalformedMessageException("Message is not valid.");
		}
	}

	public FireResponse processEnemyFireAttempt(String message) throws MalformedMessageException {
		Position enemyShootAt = MessageParser.parsePosition(message);
		return field.getResultFromEnemyFire(enemyShootAt);
	}

	public Position getFirePosition() {
		return shooter.shoot();
	}

	public boolean isOver() {
		return gameOver;
	}

	public String getPrintableBattlefield() {
		return field.toString();
	}
}
