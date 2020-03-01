package com.travix.medusa.busyflights.threads;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.enums.cabinClass.CabinClass;
import com.travix.medusa.busyflights.enums.suppliers.Supplier;

public class ToughJetProcessorTest {
	
	private ToughJetProcessor toughJetProcessor;
	private BusyFlightsRequest busyFlightsRequest;
	private ToughJetResponse[] toughJetResponses;
	
	@Before
	public void setup() {
		String url = "http://test";
		busyFlightsRequest = new BusyFlightsRequest();
		busyFlightsRequest.setOrigin("LHR");
		busyFlightsRequest.setDestination("PRG");
		busyFlightsRequest.setDepartureDate("2020-01-25");
		busyFlightsRequest.setReturnDate("2020-01-28");
		busyFlightsRequest.setNumberOfPassengers(2);
		
		toughJetProcessor = new ToughJetProcessor(busyFlightsRequest, url);
		
		toughJetResponses = new ToughJetResponse[1];
		ToughJetResponse flight_001 = new ToughJetResponse();
		flight_001.setOutboundDateTime("2020-01-25T08:05:23Z");
		flight_001.setInboundDateTime("2020-01-28T12:15:23Z");
		flight_001.setDepartureAirportName("LHR");
		flight_001.setArrivalAirportName("PRG");
		flight_001.setCarrier("Air Berlin");
		flight_001.setBasePrice(300);
		flight_001.setDiscount(0.1);
		flight_001.setTax(0.1);
		toughJetResponses[0] = flight_001;
	}
	
	@Test
	public void converRequestTest() {
		ToughJetRequest toughJetRequest = toughJetProcessor.converRequest(busyFlightsRequest);
		Assert.assertEquals("LHR", toughJetRequest.getFrom());
		Assert.assertEquals("PRG", toughJetRequest.getTo());
		Assert.assertEquals("2020-01-25", toughJetRequest.getOutboundDate());
		Assert.assertEquals("2020-01-28", toughJetRequest.getInboundDate());
		Assert.assertEquals(2, toughJetRequest.getNumberOfAdults());
	}
	
	@Test
	public void convertResponseTest () {
		List<BusyFlightsResponse> convertResponse = toughJetProcessor.convertResponse(toughJetResponses);
		BusyFlightsResponse busyFlightsResponse = convertResponse.get(0);
		Assert.assertEquals("LHR", busyFlightsResponse.getDepartureAirportCode());
		Assert.assertEquals("PRG", busyFlightsResponse.getDestinationAirportCode());
		Assert.assertEquals("2020-01-25T08:05:23Z", busyFlightsResponse.getDepartureDate());
		Assert.assertEquals("2020-01-28T12:15:23Z", busyFlightsResponse.getArrivalDate());
		Assert.assertEquals(297.0, busyFlightsResponse.getFare(), 0.1);
		Assert.assertEquals("Air Berlin", busyFlightsResponse.getAirLine());
		Assert.assertEquals(Supplier.ToughJet.getSupplierCode(), busyFlightsResponse.getSupplier());
	}
	

}
