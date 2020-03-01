package com.travix.medusa.busyflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.engine.ICrazyAirSearchEngine;

@RestController
@RequestMapping("/crazyair")
public class CrazyAirController {
	
	@Autowired
	private ICrazyAirSearchEngine crazyAirSearchEngine;

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	public List<CrazyAirResponse> searchFlights(@RequestBody CrazyAirRequest crazyAirRequest){
		return crazyAirSearchEngine.find(crazyAirRequest);
		
	}
}
