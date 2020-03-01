package com.travix.medusa.busyflights.engine;

import java.util.List;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

public interface ICrazyAirSearchEngine {
	
	public List<CrazyAirResponse> find(CrazyAirRequest crazyAirRequest);

}
