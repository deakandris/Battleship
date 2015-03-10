package com.epam.cleancode.torpedo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.epam.cleancode.torpedo.ship.Ship;
import com.epam.cleancode.torpedo.ship.ShipPart;
import com.epam.cleancode.torpedo.util.Position;

public class BattlefieldTest {
	
	private Battlefield underTest;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void startUp() {
		underTest = new Battlefield(10, 10);
	}
	
	@Test
	public void testConstructorWithNonpositiveNumbers() {
		// GIVEN
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Arguments must be greater than 0.");
		// WHEN
		underTest = new Battlefield(-1, 0);
		//THEN
	}
	
	@Test
	public void testAddShipToFieldShouldAddShipToListOfShipsWhenInsideBoundsAndNotColliding() {
		// GIVEN
		Ship mockShip = EasyMock.createMock(Ship.class);
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		Position mockPosition = EasyMock.createMock(Position.class);
		EasyMock.expect(mockShip.getHull()).andReturn(Arrays.asList(mockPart));
		EasyMock.expect(mockPart.getPosition()).andReturn(mockPosition);
		EasyMock.expect(mockPosition.getX()).andReturn(1);
		EasyMock.expect(mockPosition.getY()).andReturn(1);
		EasyMock.replay(mockShip, mockPart, mockPosition);
		// WHEN
		underTest.addShipToField(mockShip);
		// THEN
		EasyMock.verify(mockShip, mockPart, mockPosition);
		boolean result = underTest.getShips().contains(mockShip);
		assertTrue(result);
	}
	
	@Test
	public void testAddShipToFieldShouldThrowIllegalArgumentExceptionWhenOutsideBounds() {
		// GIVEN
		Ship mockShip = EasyMock.createMock(Ship.class);
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		Position mockPosition = EasyMock.createMock(Position.class);
		EasyMock.expect(mockShip.getHull()).andReturn(Arrays.asList(mockPart));
		EasyMock.expect(mockPart.getPosition()).andReturn(mockPosition);
		EasyMock.expect(mockPosition.getX()).andReturn(1);
		EasyMock.expect(mockPosition.getY()).andReturn(-1);
		EasyMock.replay(mockShip, mockPart, mockPosition);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Ship is outside of the battlefield's bounds.");
		// WHEN
		underTest.addShipToField(mockShip);
		// THEN
		// exeption thrown
	}
	
	@Test
	public void testAddShipToFieldShouldThrowIllegalArgumentExceptionWhenColliding() {
		// GIVEN
		Ship mockShip = EasyMock.createMock(Ship.class);
		ShipPart mockPart = EasyMock.createMock(ShipPart.class);
		Position mockPosition = EasyMock.createMock(Position.class);
		EasyMock.expect(mockShip.getHull()).andReturn(Arrays.asList(mockPart)).times(2);
		EasyMock.expect(mockPart.getPosition()).andReturn(mockPosition).times(2);
		EasyMock.expect(mockPosition.getX()).andReturn(1).times(2);
		EasyMock.expect(mockPosition.getY()).andReturn(1).times(2);
		EasyMock.expect(mockShip.isTooClose(mockShip)).andReturn(true);
		EasyMock.replay(mockShip, mockPart, mockPosition);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Ship is colliding with an other ship.");
		underTest.addShipToField(mockShip);
		// WHEN
		underTest.addShipToField(mockShip);
		// THEN
		// exeption thrown
	}
	
	@After
	public void tearDown() {
		underTest = null;
	}
}
