package com.epam.cleancode.torpedo.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.cleancode.torpedo.Game;
import com.epam.cleancode.torpedo.connection.FireResponse;
import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipParser;
import com.epam.cleancode.torpedo.util.FileReaderFactory;
import com.epam.cleancode.torpedo.util.MalformedMessageException;
import com.epam.cleancode.torpedo.util.Position;
import com.epam.cleancode.torpedo.util.ShipParserException;

public abstract class Application implements Runnable {

	private static final String FILE = "ships.txt";

	protected static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	protected final Game game;
	protected final BufferedReader in;
	protected final PrintWriter out;

	protected Application(final int width, final int height, BufferedReader in, PrintWriter out) {
		super();
		List<Ship> ships = null;
		try {
			BufferedReader reader = FileReaderFactory.createReader(FILE);
			ships = ShipParser.parseShips(reader);
		} catch (IOException e) {
			throw new ShipParserException();
		}
		this.game = new Game(width, height, ships);
		this.in = in;
		this.out = out;
	}

	protected abstract void startUp() throws IOException, MalformedMessageException;

	@Override
	public void run() {
		LOGGER.info("Application started");
		try {
		startUp();

		while (gameIsNotOver()) {
			respondEnemyFireAttempt();
			if (gameIsNotOver()) {
				fire();
			}
		}
		LOGGER.info("Final status:\n\n" + game);
		} catch (MalformedMessageException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	protected boolean gameIsNotOver() {
		return !game.isOver();
	}

	protected void respondEnemyFireAttempt() throws IOException, MalformedMessageException {
		String enemyFireMsg = in.readLine();
		FireResponse fireResponse = game.processEnemyFireAttempt(enemyFireMsg);
		out.println(fireResponse);
		LOGGER.info("Enemy: " + enemyFireMsg +" | Response: " + fireResponse);
	}
	
	protected void fire() throws IOException, MalformedMessageException {
		Position myFirePosition = game.getFirePosition();
		out.println("FIRE " + myFirePosition);
		String myFireResult = in.readLine();
		game.processMyFireResult(myFireResult);
		LOGGER.info("Me: FIRE " + myFirePosition + " | Response: " + myFireResult);
	}

}
