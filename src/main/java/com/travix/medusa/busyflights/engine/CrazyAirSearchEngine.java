package com.travix.medusa.busyflights.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.db.ICrazyAirDBService;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

@Service
public class CrazyAirSearchEngine implements ICrazyAirSearchEngine{
	
	@Autowired
	private ICrazyAirDBService crazyAirDBService;

	@Override
	public List<CrazyAirResponse> find(CrazyAirRequest crazyAirRequest) {
		return crazyAirDBService.filter(crazyAirRequest);
	}

	

}
