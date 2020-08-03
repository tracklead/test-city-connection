package org.test.rgoenka.cityconnection.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CityConnectionsTest {

	CityConnections cityConnections = new CityConnections();
	
	@Test
	public void testAddBiDirectionalPathEntries() {
		String source = "Boston";
		String destination = "Seattle";
		
		cityConnections.addCityPath(source, destination);
		
		assertTrue(cityConnections.areCitiesConnected(source, destination));
		assertTrue(cityConnections.areCitiesConnected(destination, source));
	}

	@Test
	public void testCannotAddSameCityAsSourceAndDestination() {
		String source = "boston";
		String destination = "boston";
		
		cityConnections.addCityPath(source, destination);
		
		assertFalse(cityConnections.getConnectedCities(source).isPresent());
		assertFalse(cityConnections.getConnectedCities(destination).isPresent());
	}

	@Test
	public void testSameCityAsSourceAndDestinationIsConnected() {
		String source = "boston";
		String destination = "boston";
		
		cityConnections.addCityPath(source, destination);
		
		assertTrue(cityConnections.areCitiesConnected(source, destination));
		assertTrue(cityConnections.areCitiesConnected(destination, source));
		
	}

	@Test
	public void testCityNamesAreCaseInsensitive() {
		String source = "BostOn";
		String destination = "SeATtlE";
		
		cityConnections.addCityPath(source, destination);
		
		assertTrue(cityConnections.areCitiesConnected("BOSTOn", "sEATTLE"));
		assertTrue(cityConnections.areCitiesConnected("SeAtTlE", "bOsToN"));
	}


	@Test
	public void testMultiplePaths() {
		String source = "Boston";
		String destination1 = "Seattle";
		String destination2 = "Colorado Springs";
		
		cityConnections.addCityPath(source, destination1);
		cityConnections.addCityPath(source, destination2);
		
		assertTrue(cityConnections.areCitiesConnected(source, destination1));
		assertTrue(cityConnections.areCitiesConnected(destination1, source));
		assertTrue(cityConnections.areCitiesConnected(source, destination2));
		assertTrue(cityConnections.areCitiesConnected(destination2, source));
		assertFalse(cityConnections.areCitiesConnected(destination1, destination2));
		assertFalse(cityConnections.areCitiesConnected(destination2, destination1));
	}
}
