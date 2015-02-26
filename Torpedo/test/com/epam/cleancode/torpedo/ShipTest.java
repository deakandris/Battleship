package com.epam.cleancode.torpedo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.util.Position;

public class ShipTest {

	private Ship underTest;
	
	@Before
	public void startUp() {
		
	}
	
	@Test
	public void testIsHitShouldReturnTrueWhenArgumentIsInsideHull() {
		// GIVEN
		Position toCheck = new Position(1, 2);
		underTest = new Ship(toCheck);
		// WHEN
		boolean result = underTest.isHit(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testIsHitShouldReturnFalseWhenArgumentIsOutsideHull() {
		// GIVEN
		Position toCheck = new Position(0, 0);
		underTest = new Ship(new Position(1, 2));
		// WHEN
		boolean result = underTest.isHit(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@Test
	public void testIsTooCloseShouldReturnTrueWhenArgumentIsOneCoordinateAwayFromHull() {
		// GIVEN
		// TODO mock position!
		Ship toCheck = new Ship(new Position(1, 1));
		underTest = new Ship(new Position(1, 2));
		// WHEN
		boolean result = underTest.isTooClose(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testIsTooCloseShouldReturnFalseWhenArgumentIsMoreThanOneCoordinateAwayFromHull() {
		// GIVEN
		// TODO mock position!
		Ship toCheck = new Ship(new Position(1, 0));
		underTest = new Ship(new Position(1, 2));
		// WHEN
		boolean result = underTest.isTooClose(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@Test
	public void testIncreaseNumberOfHits() {
		// GIVEN
		underTest = new Ship(new Position(1, 2));
		// WHEN
		underTest.increaseNumberOfHits();
		int result = underTest.getNumberOfHits();
		// THEN
		assertEquals(1, result);
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
}
