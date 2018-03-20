package main.model.advancedObjects;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;
import main.model.simulatedObjects.Vehicle;

public class MostCrowded extends Junction{

	public MostCrowded(String id) {
		super(id);
		// TODO Auto-generated constructor stub
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
