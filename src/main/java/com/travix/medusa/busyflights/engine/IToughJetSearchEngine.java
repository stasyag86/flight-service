package com.travix.medusa.busyflights.engine;

import java.util.List;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public interface IToughJetSearchEngine {
	
	public List<ToughJetResponse> find(ToughJetRequest toughJetRequest);

}
