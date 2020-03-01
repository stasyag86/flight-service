package com.travix.medusa.busyflights.db;

import java.util.List;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public interface IToughJetDBService {
	
public List<ToughJetResponse> filter (ToughJetRequest toughJetRequest);
	
	public List<ToughJetResponse> getAll();

}
