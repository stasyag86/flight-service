package com.travix.medusa.busyflights.db;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

@Repository
public class ToughJetDBService implements IToughJetDBService{
	
	private List<ToughJetResponse> toughJetResponses;
	
	@PostConstruct
	public void initData () {
		createData();
	}

	@Override
	public List<ToughJetResponse> filter(ToughJetRequest toughJetRequest) {
		List<ToughJetResponse> filterByAirport = toughJetResponses.stream()
				.filter(isSameOriginAndDestination(toughJetRequest))
				.collect(Collectors.toList());
		List<ToughJetResponse> filterByDate = filterByAirport.stream()
				.filter(isDateInRange(toughJetRequest))
				.collect(Collectors.toList());
		return filterByDate;
	}

	@Override
	public List<ToughJetResponse> getAll() {
		return toughJetResponses;
	}
	
	private Predicate<ToughJetResponse> isSameOriginAndDestination(ToughJetRequest toughJetRequest){
		return f -> f.getDepartureAirportName().equalsIgnoreCase(toughJetRequest.getFrom()) && 
				f.getArrivalAirportName().equalsIgnoreCase(toughJetRequest.getTo());
		
	}
	
	private Predicate<ToughJetResponse> isDateInRange (ToughJetRequest toughJetRequest){
		LocalDate departureDate = LocalDate.parse(toughJetRequest.getOutboundDate(), DateTimeFormatter.ISO_DATE);
		LocalDate returnDate = LocalDate.parse(toughJetRequest.getInboundDate(), DateTimeFormatter.ISO_DATE);
		return f -> LocalDate.parse(f.getOutboundDateTime(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())).isEqual(departureDate) &&
				LocalDate.parse(f.getInboundDateTime(), DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())).isEqual(returnDate);
	}
	
	private void createData() {
		toughJetResponses = new ArrayList<ToughJetResponse>();
		ToughJetResponse flight_001 = new ToughJetResponse();
		flight_001.setOutboundDateTime("2020-01-01T08:05:23Z");
		flight_001.setInboundDateTime("2020-01-10T12:15:23Z");
		flight_001.setDepartureAirportName("TXL");
		flight_001.setArrivalAirportName("AMS");
		flight_001.setCarrier("Air Berlin");
		flight_001.setBasePrice(300);
		flight_001.setDiscount(0.1);
		flight_001.setTax(0.1);
		toughJetResponses.add(flight_001);
		
		ToughJetResponse flight_002 = new ToughJetResponse();
		flight_002.setOutboundDateTime("2020-01-01T08:05:23Z");
		flight_002.setInboundDateTime("2020-01-10T12:15:23Z");
		flight_002.setDepartureAirportName("TXL");
		flight_002.setArrivalAirportName("AMS");
		flight_002.setCarrier("Air France");
		flight_002.setBasePrice(350);
		flight_002.setDiscount(0.15);
		flight_002.setTax(0.12);
		toughJetResponses.add(flight_002);
		
		ToughJetResponse flight_003 = new ToughJetResponse();
		flight_003.setOutboundDateTime("2020-01-15T08:05:23Z");
		flight_003.setInboundDateTime("2020-01-25T12:15:23Z");
		flight_003.setDepartureAirportName("BUD");
		flight_003.setArrivalAirportName("FCO");
		flight_003.setCarrier("Ryanair");
		flight_003.setBasePrice(150);
		flight_003.setDiscount(0.05);
		flight_003.setTax(0.2);
		toughJetResponses.add(flight_003);
		
	}

	protected void setToughJetResponses(List<ToughJetResponse> toughJetResponses) {
		this.toughJetResponses = toughJetResponses;
	}
	
	

}
