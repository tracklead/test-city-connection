package org.test.rgoenka.cityconnection.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.test.rgoenka.cityconnection.model.CityConnections;

public class CityPathVerifierServiceTest {

	CityPathVerifierService cityPathVerifierService;
	
	@BeforeEach
	public void setupCityConnections() {
		cityPathVerifierService = new CityPathVerifierService();
		CityConnections cityConnections = new CityConnections();
		cityPathVerifierService.setCityConnections(cityConnections);
		populateCityConnectionsForTesting(cityConnections);
	}
	
	private void populateCityConnectionsForTesting(CityConnections cityConnections) {
		String boston = "Boston";
		String seattle = "Seattle";
		String portland = "Portland";
		String nevada = "Nevada";
		String alBuquerue = "Al Buquerque";
		String austin = "austin";
		String lasVegas = "Las Vegas";
		String phoenix = "phoenix";
		String omaha = "omaha";
		String saltLakeCity = "Salt Lake City";
		String coloradoSprings = "Colorado Springs";
		String chicago = "Chicago";
		String dallas = "Dallas";
		
		cityConnections.addCityPath(seattle, portland);
		//cityConnections.addCityPath(portland, alBuquerue);
		cityConnections.addCityPath(alBuquerue, austin);
		cityConnections.addCityPath(alBuquerue, saltLakeCity);
		cityConnections.addCityPath(portland, lasVegas);
		cityConnections.addCityPath(lasVegas, phoenix);
		cityConnections.addCityPath(portland, saltLakeCity);
		cityConnections.addCityPath(saltLakeCity, coloradoSprings);
		cityConnections.addCityPath(saltLakeCity, alBuquerue);
		cityConnections.addCityPath(portland, nevada);
		cityConnections.addCityPath(omaha, chicago);
		cityConnections.addCityPath(omaha, dallas);
	}

	@Test
	public void testIsCityConnectedDirect() {
		assertTrue(cityPathVerifierService.areCitiesConnected("portland", "nevada"));
		assertTrue(cityPathVerifierService.areCitiesConnected("al buquerque", "salt lake city"));
	}

	@Test
	public void testIsCityConnectedIndirect() {
		assertTrue(cityPathVerifierService.areCitiesConnected("seattle", "al buquerque"));
		assertTrue(cityPathVerifierService.areCitiesConnected("seattle", "phoenix"));
	}

	@Test
	public void testIsCityConnectedDirectReverse() {
		assertTrue(cityPathVerifierService.areCitiesConnected("colorado springs", "portland"));
		assertTrue(cityPathVerifierService.areCitiesConnected("las vegas", "portland"));
	}

	@Test
	public void testIsCityConnectedIndirectReverse() {
		assertTrue(cityPathVerifierService.areCitiesConnected("austin", "seattle"));
		assertTrue(cityPathVerifierService.areCitiesConnected("al buquerque", "colorado springs"));
	}

	@Test
	public void testMissingCityReportsNotConnected() {
		assertFalse(cityPathVerifierService.areCitiesConnected("portland", "chicago"));
		assertFalse(cityPathVerifierService.areCitiesConnected("austin", "dallas"));
	}

	@Test
	public void testIsCityNotSpecified() {
		assertFalse(cityPathVerifierService.areCitiesConnected("portland", "new york"));
		assertFalse(cityPathVerifierService.areCitiesConnected("austin", "new jersey"));
	}

	@Test
	public void testSameCityIsConnectedAsSourceAndDestination() {
		assertTrue(cityPathVerifierService.areCitiesConnected("austin", "AUSTIN"));
		assertTrue(cityPathVerifierService.areCitiesConnected("SEATTLE", "seattle"));
		
	}

}
