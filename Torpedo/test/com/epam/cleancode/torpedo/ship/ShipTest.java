package com.epam.cleancode.torpedo.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipPart;
import com.epam.cleancode.torpedo.util.Position;

public class ShipTest {

	private Ship underTest;
	
	@Before
	public void startUp() {
		
	}
	
	@Test
	public void testHaveBeenShotAtShouldReturnTrueWhenArgumentIsInsideHull() {
		// GIVEN
		Position toCheck = new Position(1, 2);
		underTest = new Ship(toCheck);
		// WHEN
		boolean result = underTest.haveBeenShotAt(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testHaveBeenShotAtShouldReturnFalseWhenArgumentIsOutsideHull() {
		// GIVEN
		Position toCheck = new Position(0, 0);
		underTest = new Ship(new Position(1, 2));
		// WHEN
		boolean result = underTest.haveBeenShotAt(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@Test
	public void testIsTooCloseShouldReturnTrueWhenArgumentIsAdjacentToAnyPartOfHull() {
		// GIVEN
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		Position mockPosition = EasyMock.createMock(Position.class);
		Ship toCheck = new Ship(mockPart);
		underTest = new Ship(mockPart);
		EasyMock.expect(mockPart.getPosition()).andReturn(mockPosition);
		EasyMock.expect(mockPart.isAdjacent(mockPosition)).andReturn(true);
		EasyMock.replay(mockPart);
		// WHEN
		boolean result = underTest.isTooClose(toCheck);
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testIsTooCloseShouldReturnFalseWhenArgumentIsNotAdjacentToAnyPartOfHull() {
		// GIVEN
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		Position mockPosition = EasyMock.createMock(Position.class);
		Ship toCheck = new Ship(mockPart);
		underTest = new Ship(mockPart);
		EasyMock.expect(mockPart.getPosition()).andReturn(mockPosition);
		EasyMock.expect(mockPart.isAdjacent(mockPosition)).andReturn(false);
		EasyMock.replay(mockPart);
		// WHEN
		boolean result = underTest.isTooClose(toCheck);
		// THEN
		assertFalse(result);
	}
	
	@Test
	public void testGetNumberOfHitsShouldReturnNumberOfDamagedParts() {
		// GIVEN
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		underTest = new Ship(mockPart);
		EasyMock.expect(mockPart.isDamaged()).andReturn(true);
		EasyMock.replay(mockPart);
		// WHEN
		int result = underTest.getNumberOfHits();
		// THEN
		assertEquals(1, result);
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
}
