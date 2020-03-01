package com.travix.medusa.busyflights.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.enums.suppliers.Supplier;

public class ToughJetProcessor implements Callable<List<BusyFlightsResponse>>{
	
	private BusyFlightsRequest busyFlightsRequest; 
	private String url; 
	
	public ToughJetProcessor(BusyFlightsRequest busyFlightsRequest, String url) {
		this.busyFlightsRequest = busyFlightsRequest;
		this.url = url;
	}

	@Override
	public List<BusyFlightsResponse> call() throws Exception {
		ToughJetRequest request = converRequest(busyFlightsRequest);
		ToughJetResponse[] toughJetResponse = initCall(request);
		return convertResponse(toughJetResponse);
	}
	
	protected ToughJetRequest converRequest (BusyFlightsRequest busyFlightsRequest) {
		ToughJetRequest toughJetRequest = new ToughJetRequest();
		toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
		toughJetRequest.setTo(busyFlightsRequest.getDestination());
		toughJetRequest.setOutboundDate(busyFlightsRequest.getDepartureDate());
		toughJetRequest.setInboundDate(busyFlightsRequest.getReturnDate());
		toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
		return toughJetRequest;
		
	}
	
	private ToughJetResponse[] initCall (ToughJetRequest toughJetRequest) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ToughJetResponse[]> responseEntity = restTemplate.postForEntity(url, toughJetRequest, ToughJetResponse[].class);
		return responseEntity.getBody();
		
	}
	
	protected List<BusyFlightsResponse> convertResponse(ToughJetResponse[] toughJetResponses) {
		List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<BusyFlightsResponse>();
		for (ToughJetResponse toughJetResponse : toughJetResponses) {
			BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse(Supplier.ToughJet.getSupplierCode());
			busyFlightsResponse.setAirLine(toughJetResponse.getCarrier());
			busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime());
			busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime());
			busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
			busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
			busyFlightsResponse.setFare(calcPrice(toughJetResponse));
			busyFlightsResponses.add(busyFlightsResponse);
		}
		return busyFlightsResponses;
		
	}
	
	private double calcPrice (ToughJetResponse toughJetResponse) {
		double totalPrice = toughJetResponse.getBasePrice();
		Double tax = toughJetResponse.getTax();
		Double discount = toughJetResponse.getDiscount();
		if (tax != null) {
			totalPrice = totalPrice + totalPrice * tax;
		}
		if (discount != null) {
			totalPrice = totalPrice - totalPrice * discount;
		}
		return totalPrice;
	}

}
