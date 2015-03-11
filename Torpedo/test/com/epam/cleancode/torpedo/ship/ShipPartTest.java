package com.epam.cleancode.torpedo.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void testMoveByShouldModifyCoordinatesByTheGivenAmount() {
		// GIVEN
		final int x = 1;
		final int y = 2;
		underTest = new ShipPart(1, 2);
		final int dx = 3;
		final int dy = 4;
		// WHEN
		underTest.moveBy(dx, dy);
		// THEN
		Position newPosition = underTest.getPosition();
		assertEquals(x + dx, newPosition.getX());
		assertEquals(y + dy, newPosition.getY());
	}
	
	@Test
	public void testCloneShouldCreateCloneProperly() {
		// GIVEN
		underTest = new ShipPart(1, 2);
		// WHEN
		ShipPart clone = underTest.clone();
		// THEN
		boolean isSameObject = clone == underTest;
		assertFalse(isSameObject);
		assertEquals(underTest, clone);
	}

	@After
	public void tearDown() {
		underTest = null;
	}

}
