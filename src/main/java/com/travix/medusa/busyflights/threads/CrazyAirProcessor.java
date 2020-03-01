package com.travix.medusa.busyflights.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.enums.suppliers.Supplier;


public class CrazyAirProcessor implements Callable<List<BusyFlightsResponse>>{
	
	private BusyFlightsRequest busyFlightsRequest;
	private String url; 
	
	

	public CrazyAirProcessor(BusyFlightsRequest busyFlightsRequest, String url) {
		this.busyFlightsRequest = busyFlightsRequest;
		this.url = url;
	}

	@Override
	public List<BusyFlightsResponse> call() throws Exception {
		CrazyAirRequest request = converRequest(busyFlightsRequest);
		CrazyAirResponse[] crazyAirResponse = initCall(request);
		return convertResponse(crazyAirResponse);
	}
	
	protected CrazyAirRequest converRequest (BusyFlightsRequest busyFlightsRequest) {
		CrazyAirRequest crazyAirRequest = new CrazyAirRequest();
		crazyAirRequest.setOrigin(busyFlightsRequest.getOrigin());
		crazyAirRequest.setDestination(busyFlightsRequest.getDestination());
		crazyAirRequest.setDepartureDate(busyFlightsRequest.getDepartureDate());
		crazyAirRequest.setReturnDate(busyFlightsRequest.getReturnDate());
		crazyAirRequest.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
		return crazyAirRequest;
		
	}
	
	private CrazyAirResponse[] initCall (CrazyAirRequest crazyAirRequest) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CrazyAirResponse[]> responseEntity = restTemplate.postForEntity(url, crazyAirRequest, CrazyAirResponse[].class);
		return responseEntity.getBody();

		
	}
	
	protected List<BusyFlightsResponse> convertResponse(CrazyAirResponse[] crazyAirResponses) {
		List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<BusyFlightsResponse>();
		for (CrazyAirResponse crazyAirResponse : crazyAirResponses) {
			BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse(Supplier.CrazyAir.getSupplierCode());
			busyFlightsResponse.setAirLine(crazyAirResponse.getAirline());
			busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate());
			busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate());
			busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
			busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
			busyFlightsResponse.setFare(crazyAirResponse.getPrice());
			busyFlightsResponses.add(busyFlightsResponse);
		}
		return busyFlightsResponses;
	}

}
