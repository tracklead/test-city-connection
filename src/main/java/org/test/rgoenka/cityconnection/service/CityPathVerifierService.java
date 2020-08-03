package org.test.rgoenka.cityconnection.service;

import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.rgoenka.cityconnection.model.CityConnections;

@Service
public class CityPathVerifierService {

	@Autowired
	CityConnections cityConnections;

	public boolean areCitiesConnected(String source, String destination) {
		if (cityConnections.areCitiesConnected(source, destination)) {
			return true;
		}
		Stack<String> travelledCities = new Stack<>();
		travelledCities.add(source);
		return areCitiesConnected(travelledCities, new TreeSet<String>(), destination);
	}
	
	private boolean areCitiesConnected(Stack<String> travelledCities, Set<String> noPathCities, 
			String destination) {
		
		if (travelledCities.size() < 1) {
			return true;
		}
		String source = travelledCities.peek();
		if (cityConnections.areCitiesConnected(source, destination)) {
			return true;
		}

		Optional<Set<String>> toCities = cityConnections.getConnectedCities(source);
		if (toCities.isPresent()) {
			for (String toCity : toCities.get()) {
				if (travelledCities.contains(toCity)) {
					continue;
				}
				if (noPathCities.contains(toCity)) {
					continue;
				}
				travelledCities.push(toCity);
				if (areCitiesConnected(travelledCities, noPathCities, destination)) {
					return true;
				}
				noPathCities.add(travelledCities.pop()); // Remove current city, already checked
			}
		}
		return false;
	}

	public CityConnections getCityConnections() {
		return cityConnections;
	}

	public void setCityConnections(CityConnections cityConnections) {
		this.cityConnections = cityConnections;
	}
}
