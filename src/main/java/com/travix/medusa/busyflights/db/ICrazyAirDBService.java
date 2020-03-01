package com.travix.medusa.busyflights.db;

import java.util.List;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

public interface ICrazyAirDBService {
	
	public List<CrazyAirResponse> filter (CrazyAirRequest crazyAirRequest);
	
	public List<CrazyAirResponse> getAll();

}
