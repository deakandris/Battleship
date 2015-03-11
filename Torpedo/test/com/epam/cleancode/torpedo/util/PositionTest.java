package com.epam.cleancode.torpedo.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	Position underTest;
	
	@Before
	public void startUp() {
		underTest = new Position(1,1);
	}
	
	@Test
	public void testIsAdjacentShouldReturnTrueWhenArgumentIsLessThanTwoCoordinatesAway() {
		// GIVEN
		Position toCheck = new Position(0,0);
		// WHEN
		boolean result = underTest.isAdjacent(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testIsAdjacentShouldReturnFalseWhenArgumentIsMoreThanOneCoordinatesAway() {
		// GIVEN
		Position toCheck = new Position(-1,0);
		// WHEN
		boolean result = underTest.isAdjacent(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
}
