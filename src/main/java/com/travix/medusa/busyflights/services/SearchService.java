package com.travix.medusa.busyflights.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.threads.CrazyAirProcessor;
import com.travix.medusa.busyflights.threads.ToughJetProcessor;

@Service
public class SearchService implements ISearchService{
	
	
	@Value("${crazyair.url}")
    private String crazyAirUrl;
	
	@Value("${toughjet.url}")
    private String toughJetUrl;

	@Override
	public List<BusyFlightsResponse> find(BusyFlightsRequest busyFlightsRequest) {
		List<BusyFlightsResponse> resList = new ArrayList<BusyFlightsResponse>();
		
		Set<Callable<List<BusyFlightsResponse>>> callabelProcessors = initProcessors(busyFlightsRequest);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		try {
			List<Future<List<BusyFlightsResponse>>> futures = executorService.invokeAll(callabelProcessors);
			for (Future<List<BusyFlightsResponse>> future : futures) {
				while (!future.isDone()) {
					Thread.sleep(100);
				}
				resList.addAll(future.get());
			}
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		
		Collections.sort(resList); 
		return resList;
	}
	
	private Set<Callable<List<BusyFlightsResponse>>> initProcessors (BusyFlightsRequest busyFlightsRequest){
		CrazyAirProcessor crazyAirProcessor = new CrazyAirProcessor(busyFlightsRequest, crazyAirUrl);
		ToughJetProcessor toughJetProcessor = new ToughJetProcessor(busyFlightsRequest, toughJetUrl);
		Set<Callable<List<BusyFlightsResponse>>> callabelProcessors = new HashSet<Callable<List<BusyFlightsResponse>>>();
		callabelProcessors.add(crazyAirProcessor);
		callabelProcessors.add(toughJetProcessor);
		return callabelProcessors;
	}

}
