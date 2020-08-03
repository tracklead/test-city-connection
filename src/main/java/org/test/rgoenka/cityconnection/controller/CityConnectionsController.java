package org.test.rgoenka.cityconnection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.rgoenka.cityconnection.service.CityPathVerifierService;

@RestController
public class CityConnectionsController {

	@Autowired
	private CityPathVerifierService cityPathVerifierService;

	/**
	 * @param originCity
	 * @param destinationCity
	 * @return a simple yes/no answer 
	 * 	- yes if origin and destination city are connected via roads, else return no
	 */
	@GetMapping(path = "/connected")
	public String isConnected(@RequestParam("origin") String originCity,
			@RequestParam("destination") String destinationCity) {
		return cityPathVerifierService.areCitiesConnected(originCity, destinationCity) ? 
				"yes" : "no";
	}
}