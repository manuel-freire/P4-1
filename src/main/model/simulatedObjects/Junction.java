package main.model.simulatedObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.model.TrafficSimulator;

import java.util.NoSuchElementException;

public class Junction {
	private Map<List<Vehicle>,String> queues;
	private Iterator<Map.Entry<List<Vehicle>,String>> it;
	private String id;
	
	/**
	 * Class constructor.
	 * @param id identification of the junction
	 */
	public Junction(String id) {
		it = queues.entrySet().iterator();
	}
	
	/**
	 * Returns the identification of the junction.
	 * @return identification of the junction
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Adds a road to the incoming roads.
	 * @param road_id incoming road's id
	 */
	public void addRoad(String road_id) {
		queues.put(new ArrayList<Vehicle>(), road_id);
	}
	/**
	 * Enters a vehicle in the junction.
	 * @param vehicle vehicle to be included
	 * @param r road it came from
	 */
	public void enterVehicle(Vehicle vehicle, String road_id) {
		for (Map.Entry<List<Vehicle>,String> entry : queues.entrySet())
			if(entry.getValue() == road_id)
				entry.getKey().add(vehicle);
	}
	/**
	 * Moves the vehicles that can advance to the next road.
	 */
	public void advance(TrafficSimulator sim) {
		if(!it.hasNext())
			it = queues.entrySet().iterator();
		try {
		Entry<List<Vehicle>, String> pair = it.next();
		if(!pair.getKey().isEmpty()) {
			pair.getKey().get(0).advanceToNextRoad(sim);
			pair.getKey().remove(0);
		}
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.out.println("Disconnected junction. This should never happen.");
		}
	}
	/**
	 * Generates a report of the status of the road.
	 * @param time current time of the simulation.
	 */
	public String generateReport(int time) {
		/*
		 * [junction_report] 
		 * id = j2 
		 * time = 5 
		 * queues = (r1,red,[]),(r2,green,[v3,v2,v5]),(r3,red,[v1,v4]) 
		 */
		String queuesString = new String();
		Iterator<Entry<List<Vehicle>, String>> it = queues.entrySet().iterator();
		while(it.hasNext()) {
			Entry<List<Vehicle>, String> entry = it.next();
			queuesString += "(";
			for(int t = 0; t < entry.getKey().size(); t++) {
				queuesString += entry.getKey().get(t).getID();
				if(t < queues.size()-1)
					queuesString += ",";
			}
			if(it.hasNext())
				queuesString += ")";
			it.next();
		}
		return "[junction_report]\nid = " + id + "\n time = " + time + "\n queues = " + queuesString;
	}

}
