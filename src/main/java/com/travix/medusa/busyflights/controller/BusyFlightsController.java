package com.travix.medusa.busyflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.services.ISearchService;

@RestController
@RequestMapping("/search")
public class BusyFlightsController {
	
	@Autowired
	private ISearchService searchService;
	
	@RequestMapping(value = "/flight", method = RequestMethod.POST, produces = "application/json")
	public List<BusyFlightsResponse> searchFlights(@RequestBody BusyFlightsRequest busyFlightsRequest) {
		return searchService.find(busyFlightsRequest);
	}

}
