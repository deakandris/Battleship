package com.epam.cleancode.torpedo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	Position underTest;
	
	@Before
	public void startUp() {
	}
	
	@Test
	public void testIsAdjacentShouldReturnTrueWhenArgumentIsLessThanTwoCoordinatesAway() {
		// GIVEN
		Position toCheck = new Position(0,0);
		underTest = new Position(1,1);
		// WHEN
		boolean result = underTest.isAdjacent(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testIsAdjacentShouldReturnFalseWhenArgumentIsMoreThanOneCoordinatesAway() {
		// GIVEN
		Position toCheck = new Position(-1,0);
		underTest = new Position(1,1);
		// WHEN
		boolean result = underTest.isAdjacent(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@Test
	public void testMoveByShouldModifyCoordinatesByTheGivenAmount() {
		// GIVEN
		final int x = 1;
		final int y = 2;
		underTest = new Position(x,y);
		final int dx = 3;
		final int dy = 4;
		// WHEN
		underTest.moveBy(dx, dy);
		// THEN
		assertEquals(x + dx, underTest.getX());
		assertEquals(y + dy, underTest.getY());
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
}
