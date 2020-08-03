package org.test.rgoenka.cityconnection.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.test.rgoenka.cityconnection.model.CityConnectionDataException;
import org.test.rgoenka.cityconnection.model.CityConnections;

@Service
public class CityConnectionReaderService {

    @Autowired
    private CityConnections cityConnections;

    public CityConnections getCityConnections() {
		return cityConnections;
	}

	public void setCityConnections(CityConnections cityConnections) {
		this.cityConnections = cityConnections;
	}

	@Value("classpath:data/city-connection.txt")
    private Resource cityNamesFileResource;

    /**
     * Load cities mapping (connectivity) from resource file.
     * @throws IOException
     */
    @PostConstruct
    public void initialize() throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(cityNamesFileResource.getInputStream()))){
            reader.lines().forEach(this::addCities);
        }
    }

    public Resource getCityNamesFileResource() {
		return cityNamesFileResource;
	}

	public void setCityNamesFileResource(Resource cityNamesFileResource) {
		this.cityNamesFileResource = cityNamesFileResource;
	}

	private void addCities(String lineFromFile) {
        String[] cityNames = lineFromFile.trim().split("-");
        if(cityNames.length < 2){
            throw new CityConnectionDataException(String.format("City names not correctly specified, data read<%s>\n", lineFromFile));
        }
        cityConnections.addCityPath(cityNames[0].trim(), cityNames[1].trim());
    }
}
