package com.travix.medusa.busyflights.threads;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.enums.cabinClass.CabinClass;
import com.travix.medusa.busyflights.enums.suppliers.Supplier;

public class CrazyAirProcessorTest {
	
	private CrazyAirProcessor crazyAirProcessor;
	private BusyFlightsRequest busyFlightsRequest;
	private CrazyAirResponse[] crazyAirResponses;
	
	@Before
	public void setup() {
		String url = "http://test";
		busyFlightsRequest = new BusyFlightsRequest();
		busyFlightsRequest.setOrigin("LHR");
		busyFlightsRequest.setDestination("PRG");
		busyFlightsRequest.setDepartureDate("2020-01-25");
		busyFlightsRequest.setReturnDate("2020-01-28");
		busyFlightsRequest.setNumberOfPassengers(2);
		
		crazyAirProcessor = new CrazyAirProcessor(busyFlightsRequest, url);
		
		crazyAirResponses = new CrazyAirResponse[1];
		CrazyAirResponse flight_001 = new CrazyAirResponse();
		flight_001.setDepartureDate("2020-01-25T09:30:00.000-05:00");
		flight_001.setArrivalDate("2020-01-28T11:50:00.000-05:00");
		flight_001.setAirline("Lufthansa");
		flight_001.setDepartureAirportCode("LHR");
		flight_001.setDestinationAirportCode("PRG");
		flight_001.setPrice(300);
		flight_001.setCabinclass(CabinClass.Economy.getCabinClassCode());
		
		crazyAirResponses[0] = flight_001;
	}
	
	@Test
	public void converRequestTest() {
		CrazyAirRequest crazyAirRequest = crazyAirProcessor.converRequest(busyFlightsRequest);
		Assert.assertEquals("LHR", crazyAirRequest.getOrigin());
		Assert.assertEquals("PRG", crazyAirRequest.getDestination());
		Assert.assertEquals("2020-01-25", crazyAirRequest.getDepartureDate());
		Assert.assertEquals("2020-01-28", crazyAirRequest.getReturnDate());
		Assert.assertEquals(2, crazyAirRequest.getPassengerCount());
	}
	
	public void convertResponseTest () {
		List<BusyFlightsResponse> convertResponse = crazyAirProcessor.convertResponse(crazyAirResponses);
		BusyFlightsResponse busyFlightsResponse = convertResponse.get(0);
		Assert.assertEquals("LHR", busyFlightsResponse.getDepartureAirportCode());
		Assert.assertEquals("PRG", busyFlightsResponse.getDestinationAirportCode());
		Assert.assertEquals("2020-01-25T09:30:00.000-05:00", busyFlightsResponse.getDepartureDate());
		Assert.assertEquals("2020-01-28T11:50:00.000-05:00", busyFlightsResponse.getArrivalDate());
		Assert.assertEquals(300, busyFlightsResponse.getFare());
		Assert.assertEquals("Lufthansa", busyFlightsResponse.getAirLine());
		Assert.assertEquals(CabinClass.Economy.getCabinClassCode(), busyFlightsResponse.getClass());
		Assert.assertEquals(Supplier.CrazyAir.getSupplierCode(), busyFlightsResponse.getSupplier());
	}

}
