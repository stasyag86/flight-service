package com.travix.medusa.busyflights.services;

import java.util.List;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

public interface ISearchService {
	
	public List<BusyFlightsResponse> find(BusyFlightsRequest busyFlightsRequest);

}
