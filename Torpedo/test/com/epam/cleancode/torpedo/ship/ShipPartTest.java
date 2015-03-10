package com.epam.cleancode.torpedo.ship;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.epam.cleancode.torpedo.ship.ShipPart;
import com.epam.cleancode.torpedo.util.Position;

public class ShipPartTest {

	private ShipPart underTest;
	
	@Before
	public void startUp() {
		
	}
	
	@Test
	public void testIsAdjacentShouldReturnWhetherArgumentIsAdjacentToShipPartPosition() {
		// GIVEN
		Position mockPosition = EasyMock.createMock(Position.class);
		underTest = new ShipPart(mockPosition);
		EasyMock.expect(mockPosition.isAdjacent(mockPosition)).andReturn(true);
		EasyMock.replay(mockPosition);
		// WHEN
		boolean result = underTest.isAdjacent(EasyMock.anyObject(Position.class));
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void testSetDamagedShouldSetDamagedToTrue() {
		// WHEN
		underTest = new ShipPart(EasyMock.anyObject(Position.class));
		underTest.setDamaged();
		boolean result = underTest.isDamaged();
		// THEN
		assertTrue(result);
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
	
}
