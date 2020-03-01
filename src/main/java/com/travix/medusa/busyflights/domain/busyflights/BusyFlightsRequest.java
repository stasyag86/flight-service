package com.travix.medusa.busyflights.domain.busyflights;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BusyFlightsRequest {

	@Size(min = 3, max = 3)
    private String origin;
    
	@Size(min = 3, max = 3)
    private String destination;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String departureDate;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String returnDate;
    
    @Min(value = 1, message = "Min 1 passanger")
    @Max(value = 4, message = "Max 4 passangers")
    private int numberOfPassengers;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
