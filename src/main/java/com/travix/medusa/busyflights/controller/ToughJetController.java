package com.travix.medusa.busyflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.engine.IToughJetSearchEngine;

@RestController
@RequestMapping("/toughjet")
public class ToughJetController {
	
	@Autowired
	private IToughJetSearchEngine toughJetSearchEngine;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
	public List<ToughJetResponse> searchFlights(@RequestBody ToughJetRequest toughJetRequest){
		return toughJetSearchEngine.find(toughJetRequest);
		
	}

}
