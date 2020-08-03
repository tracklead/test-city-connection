package org.test.rgoenka.cityconnection.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.test.rgoenka.cityconnection.model.CityConnectionDataException;
import org.test.rgoenka.cityconnection.model.CityConnections;

public class CityConnectionReaderServiceUnitTest {

	@Mock
	private Resource cityInfoResource;

	@InjectMocks
	private CityConnectionReaderService cityConnectionReaderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGoodDataFile() throws IOException {
		CityConnections cityConnections = new CityConnections();
		InputStream is = new ByteArrayInputStream("A-B".getBytes());
		Mockito.when(cityInfoResource.getInputStream()).thenReturn(is);
		cityConnectionReaderService.setCityConnections(cityConnections);
		cityConnectionReaderService.initialize();
		Assertions.assertTrue(cityConnectionReaderService.getCityConnections().areCitiesConnected("A", "B"));
	}

	@Test
	public void testBadDataFileThrowsException() throws IOException {
		Assertions.assertThrows(CityConnectionDataException.class, () -> {
			InputStream is = new ByteArrayInputStream("A:B".getBytes());
			Mockito.when(cityInfoResource.getInputStream()).thenReturn(is);
			cityConnectionReaderService.initialize();
		  });
	}

	@Test()
	public void ioExceptionTest() throws IOException {
		Assertions.assertThrows(IOException.class, () -> {
			Mockito.when(cityInfoResource.getInputStream()).thenThrow(IOException.class);
			cityConnectionReaderService.initialize();
		  });
	}
}
