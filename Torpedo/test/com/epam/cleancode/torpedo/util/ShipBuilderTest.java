package com.epam.cleancode.torpedo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipParser;

public class ShipBuilderTest {
	
	@Test
	public void testBuildShipsShouldBuildShipsSuccessfully() {
		// GIVEN
		//@formatter:off
		String content  = ".x..\n"
						+ "....\n"
						+ "....\n"
						+ "....\n"
						+ "1\n"
						+ "....\n"
						+ ".x..\n"
						+ "....\n"
						+ "....\n"
						+ "2";
		//@formatter:on
		BufferedReader reader = new BufferedReader(new StringReader(content));
		List<Ship> result;
		try {
			// WHEN
			result = ShipParser.parseShips(reader);
			// THEN
			int firstX = result.get(0).getHull().get(0).getPosition().getX();
			int firstY = result.get(0).getHull().get(0).getPosition().getY();
			int secondX = result.get(1).getHull().get(0).getPosition().getX();
			int secondY = result.get(1).getHull().get(0).getPosition().getY();
			int thirdX = result.get(2).getHull().get(0).getPosition().getX();
			int thirdY = result.get(2).getHull().get(0).getPosition().getY();
			assertEquals(1, firstX);
			assertEquals(0, firstY);
			assertEquals(1, secondX);
			assertEquals(1, secondY);
			assertEquals(1, thirdX);
			assertEquals(1, thirdY);
		} catch (Exception e) {
			fail("Building Ships from valid input should not throw exception.");
		}
	}
}
