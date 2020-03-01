package com.travix.medusa.busyflights.db;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.enums.cabinClass.CabinClass;

@Repository
public class CrazyAirDBService implements ICrazyAirDBService{
	
	private List<CrazyAirResponse> crazyAirResponses;
	
	@PostConstruct
	public void initData () {
		createData();
	}

	@Override
	public List<CrazyAirResponse> filter(CrazyAirRequest crazyAirRequest) {
		List<CrazyAirResponse> filterByAirport = crazyAirResponses.stream()
				.filter(isSameOriginAndDestination(crazyAirRequest))
				.collect(Collectors.toList());
		List<CrazyAirResponse> filterByDate = filterByAirport.stream()
				.filter(isDateInRange(crazyAirRequest))
				.collect(Collectors.toList());
		return filterByDate;
	}

	@Override
	public List<CrazyAirResponse> getAll() {
		return crazyAirResponses;
	}
	
	private Predicate<CrazyAirResponse> isSameOriginAndDestination(CrazyAirRequest crazyAirRequest){
		return f -> f.getDepartureAirportCode().equalsIgnoreCase(crazyAirRequest.getOrigin()) && 
				f.getDestinationAirportCode().equalsIgnoreCase(crazyAirRequest.getDestination());
		
	}
	
	private Predicate<CrazyAirResponse> isDateInRange (CrazyAirRequest crazyAirRequest){
		LocalDate departureDate = LocalDate.parse(crazyAirRequest.getDepartureDate(), DateTimeFormatter.ISO_DATE);
		LocalDate returnDate = LocalDate.parse(crazyAirRequest.getReturnDate(), DateTimeFormatter.ISO_DATE);
		return f -> LocalDate.parse(f.getDepartureDate(), DateTimeFormatter.ISO_DATE_TIME).isEqual(departureDate) &&
				LocalDate.parse(f.getArrivalDate(), DateTimeFormatter.ISO_DATE_TIME).isEqual(returnDate);
	}
	
	public void createData() {
		crazyAirResponses = new ArrayList<CrazyAirResponse>();
		CrazyAirResponse flight_001 = new CrazyAirResponse();
		flight_001.setDepartureDate("2020-01-01T09:30:00.000-05:00");
		flight_001.setArrivalDate("2020-01-10T11:50:00.000-05:00");
		flight_001.setAirline("Lufthansa");
		flight_001.setDepartureAirportCode("TXL");
		flight_001.setDestinationAirportCode("AMS");
		flight_001.setPrice(300);
		flight_001.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(flight_001);
		
		CrazyAirResponse flight_002 = new CrazyAirResponse();
		flight_002.setDepartureDate("2020-01-01T08:30:00.000-05:00");
		flight_002.setArrivalDate("2020-01-10T07:50:00.000-05:00");
		flight_002.setAirline("KLM");
		flight_002.setDepartureAirportCode("TXL");
		flight_002.setDestinationAirportCode("AMS");
		flight_002.setPrice(200);
		flight_002.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(flight_002);
		
		CrazyAirResponse flight_003 = new CrazyAirResponse();
		flight_003.setDepartureDate("2020-01-15T09:30:00.000-05:00");
		flight_003.setArrivalDate("2020-01-25T11:50:00.000-05:00");
		flight_003.setAirline("Swiss");
		flight_003.setDepartureAirportCode("TXL");
		flight_003.setDestinationAirportCode("AMS");
		flight_003.setPrice(400);
		flight_003.setCabinclass(CabinClass.Business.getCabinClassCode());
		crazyAirResponses.add(flight_003);
		
		CrazyAirResponse flight_004 = new CrazyAirResponse();
		flight_004.setDepartureDate("2020-01-15T09:30:00.000-05:00");
		flight_004.setArrivalDate("2020-01-25T11:50:00.000-05:00");
		flight_004.setAirline("EasyJet");
		flight_004.setDepartureAirportCode("BUD");
		flight_004.setDestinationAirportCode("FCO");
		flight_004.setPrice(250);
		flight_004.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(flight_004);
		
		CrazyAirResponse flight_005 = new CrazyAirResponse();
		flight_005.setDepartureDate("2020-01-15T09:30:00.000-05:00");
		flight_005.setArrivalDate("2020-01-25T11:50:00.000-05:00");
		flight_005.setAirline("WizzAir");
		flight_005.setDepartureAirportCode("BUD");
		flight_005.setDestinationAirportCode("FCO");
		flight_005.setPrice(150);
		flight_005.setCabinclass(CabinClass.Economy.getCabinClassCode());
		crazyAirResponses.add(flight_005);
	}

	protected void setCrazyAirResponses(List<CrazyAirResponse> crazyAirResponses) {
		this.crazyAirResponses = crazyAirResponses;
	}
	
	

}
