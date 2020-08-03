package org.test.rgoenka.cityconnection.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class CityConnections {
	private Map<String, Set<String>> connectedCities = new TreeMap<String, Set<String>>();
	
	public void addCityPath(String source, String destination) {
		source = source.toLowerCase();
		destination = destination.toLowerCase();
		
		if (source.equals(destination)) {
			return;
		}
		addConnectedCity(source, destination);
		addConnectedCity(destination, source);
	}

	private void addConnectedCity(String source, String destination) {
		Set<String> toCities = connectedCities.get(source);
		if (toCities == null) {
			toCities =  new TreeSet<String>();
			connectedCities.put(source, toCities);
			toCities.add(destination);
		}
		else if (!toCities.contains(destination)) {
			toCities.add(destination);
		}
	}

	public Optional<Set<String>> getConnectedCities(String source) {
		return Optional.ofNullable(connectedCities.get(source));
	}
	
	public boolean areCitiesConnected(String source, String destination) {
		source = source.toLowerCase();
		destination = destination.toLowerCase();
		if (source.equals(destination)) {
			return true;
		}
		Set<String> toCities = connectedCities.get(source);
		return toCities != null && toCities.contains(destination);
	}
	
	@Override
	public String toString() {
		return connectedCities.toString();
	}
}
