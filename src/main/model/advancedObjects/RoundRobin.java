package main.model.advancedObjects;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;
import main.model.simulatedObjects.Vehicle;

public class RoundRobin extends Junction {
	private int max_time_slice, min_time_slice;
	public RoundRobin(String id, int max_time_slice, int min_time_slice) {
		super(id);
		this.max_time_slice = max_time_slice;
		this.min_time_slice = min_time_slice;
	}
	/*
	public void advance(TrafficSimulator sim) {
		if(!getIt().hasNext())
			setIt(getQueues().entrySet().iterator());
		try {
		Entry<List<Vehicle>, String> pair = getIt().next();
		if(!pair.getKey().isEmpty()) {
			pair.getKey().get(0).advanceToNextRoad(sim);
			pair.getKey().remove(0);
		}
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.out.println("Disconnected junction. This should never happen.");
		}
	}
	*/
}
