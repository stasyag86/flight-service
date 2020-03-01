package com.travix.medusa.busyflights.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.db.IToughJetDBService;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

@Service
public class ToughJetSearchEngine implements IToughJetSearchEngine{
	
	@Autowired 
	private IToughJetDBService toughJetDBService;

	@Override
	public List<ToughJetResponse> find(ToughJetRequest toughJetRequest) {
		return toughJetDBService.filter(toughJetRequest);
	}

}
