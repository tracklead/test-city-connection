package org.test.rgoenka.cityconnection.model;

public class CityConnectionDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CityConnectionDataException() {
		super();
	}

	public CityConnectionDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CityConnectionDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public CityConnectionDataException(String message) {
		super(message);
	}

	public CityConnectionDataException(Throwable cause) {
		super(cause);
	}
}
