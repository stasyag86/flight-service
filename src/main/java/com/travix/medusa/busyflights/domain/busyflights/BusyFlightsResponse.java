package com.travix.medusa.busyflights.domain.busyflights;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BusyFlightsResponse implements Comparable<BusyFlightsResponse>{
	
	private String airLine;
	
	private String supplier;
	
	private double fare;
	
	@Size(min = 3, max = 3)
	private String departureAirportCode;
	
	@Size(min = 3, max = 3)
	private String destinationAirportCode;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String departureDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String arrivalDate;

	public BusyFlightsResponse(String supplier) {
		super();
		this.supplier = supplier;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public int compareTo(BusyFlightsResponse busyFlightsResponse) {
		Double responseFare = busyFlightsResponse.getFare();
		Double thisFare = this.getFare();
		return responseFare.compareTo(thisFare);
	}
	
}
