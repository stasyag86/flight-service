package com.travix.medusa.busyflights.db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public class ToughJetDBServiceTest {
	
	private ToughJetDBService toughJetDBService = new ToughJetDBService();
	private List<ToughJetResponse> toughJetResponses = new ArrayList<ToughJetResponse>();
	private ToughJetRequest toughJetRequest;
	
	@Before
	public void setup() {
		toughJetRequest = new ToughJetRequest();
		toughJetRequest.setFrom("TXL");
		toughJetRequest.setTo("AMS");
		toughJetRequest.setOutboundDate("2020-01-15");
		toughJetRequest.setInboundDate("2020-01-25");
		toughJetRequest.setNumberOfAdults(2);
		
		ToughJetResponse matchedFlight = new ToughJetResponse();
		matchedFlight.setOutboundDateTime("2020-01-15T08:05:23Z");
		matchedFlight.setInboundDateTime("2020-01-25T12:15:23Z");
		matchedFlight.setDepartureAirportName("TXL");
		matchedFlight.setArrivalAirportName("AMS");
		matchedFlight.setCarrier("Air Berlin");
		matchedFlight.setBasePrice(300);
		matchedFlight.setDiscount(0.1);
		matchedFlight.setTax(0.1);
		toughJetResponses.add(matchedFlight);
		
		ToughJetResponse notMatchedFlight = new ToughJetResponse();
		notMatchedFlight.setOutboundDateTime("2020-01-15T08:05:23Z");
		notMatchedFlight.setInboundDateTime("2020-01-25T12:15:23Z");
		notMatchedFlight.setDepartureAirportName("FCO");
		notMatchedFlight.setArrivalAirportName("AMS");
		notMatchedFlight.setCarrier("Air France");
		notMatchedFlight.setBasePrice(350);
		notMatchedFlight.setDiscount(0.15);
		notMatchedFlight.setTax(0.12);
		toughJetResponses.add(notMatchedFlight);
		
		toughJetDBService.setToughJetResponses(toughJetResponses);
		
	}
	
	@Test
	public void filterTest() {
		List<ToughJetResponse> responses = toughJetDBService.filter(toughJetRequest);
		Assert.assertEquals(1, responses.size());
		Assert.assertEquals("Air Berlin", responses.get(0).getCarrier());
	}

}
