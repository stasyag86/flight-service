package com.travix.medusa.busyflights.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.enums.cabinClass.CabinClass;

public class CrazyAirDBServiceTest {
	
	private CrazyAirDBService crazyAirDBService = new CrazyAirDBService();
	List<CrazyAirResponse> crazyAirResponses = new ArrayList<CrazyAirResponse>();
	private CrazyAirRequest crazyAirRequest;
	
	@Before
	public void setup() {
		crazyAirRequest = new CrazyAirRequest();
		crazyAirRequest.setOrigin("TXL");
		crazyAirRequest.setDestination("AMS");
		crazyAirRequest.setDepartureDate("2020-01-15");
		crazyAirRequest.setReturnDate("2020-01-25");
		crazyAirRequest.setPassengerCount(2);
		
		CrazyAirResponse matchedFlight = new CrazyAirResponse();
		matchedFlight.setDepartureDate("2020-01-15T09:30:00.000-05:00");
		matchedFlight.setArrivalDate("2020-01-25T11:50:00.000-05:00");
		matchedFlight.setAirline("Lufthansa");
		matchedFlight.setDepartureAirportCode("TXL");
		matchedFlight.setDestinationAirportCode("AMS");
		matchedFlight.setPrice(300);
		matchedFlight.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(matchedFlight);
		
		CrazyAirResponse notMatchedFlight = new CrazyAirResponse();
		notMatchedFlight.setDepartureDate("2020-01-15T09:30:00.000-05:00");
		notMatchedFlight.setArrivalDate("2020-01-25T11:50:00.000-05:00");
		notMatchedFlight.setAirline("WizzAir");
		notMatchedFlight.setDepartureAirportCode("BUD");
		notMatchedFlight.setDestinationAirportCode("FCO");
		notMatchedFlight.setPrice(150);
		notMatchedFlight.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(notMatchedFlight);
		
		crazyAirDBService.setCrazyAirResponses(crazyAirResponses);
	}
	
	@Test
	public void filterTest() {
		List<CrazyAirResponse> responses = crazyAirDBService.filter(crazyAirRequest);
		Assert.assertEquals(1, responses.size());
		Assert.assertEquals("Lufthansa", responses.get(0).getAirline());
	}

}
