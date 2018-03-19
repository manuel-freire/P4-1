package main.model.simulatedObjects;

import java.util.ArrayList;
import java.util.HashMap;
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
		queues = new HashMap<List<Vehicle>, String>();
		it = getQueues().entrySet().iterator();
		this.id=id;
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
		getQueues().put(new ArrayList<Vehicle>(), road_id);
	}
	/**
	 * Enters a vehicle in the junction.
	 * @param vehicle vehicle to be included
	 * @param r road it came from
	 */
	public void enterVehicle(Vehicle vehicle, String road_id) {
		for (Map.Entry<List<Vehicle>,String> entry : getQueues().entrySet())
			if(entry.getValue() == road_id)
				entry.getKey().add(vehicle);
	}
	/**
	 * Moves the vehicles that can advance to the next road.
	 */
	public void advance(TrafficSimulator sim) {
		if(!getIt().hasNext())
			setIt(getQueues().entrySet().iterator());
		if(!queues.isEmpty()) {
			Entry<List<Vehicle>, String> pair = getIt().next();
			if(!pair.getKey().isEmpty()) {
				pair.getKey().get(0).advanceToNextRoad(sim);
				pair.getKey().remove(0);
			}
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
		Iterator<Entry<List<Vehicle>, String>> it = getQueues().entrySet().iterator();
		while(it.hasNext()) {
			Entry<List<Vehicle>, String> entry = it.next();
			queuesString += "(";
			for(int t = 0; t < entry.getKey().size(); t++) {
				queuesString += entry.getKey().get(t).getID();
				if(t < getQueues().size()-1)
					queuesString += ",";
			}
			if(it.hasNext())
				queuesString += ")";
		}
		return "[junction_report]id=" + id + "time=" + time + "queues=" + queuesString;
	}

	public Iterator<Map.Entry<List<Vehicle>,String>> getIt() {
		return it;
	}

	public void setIt(Iterator<Map.Entry<List<Vehicle>,String>> it) {
		this.it = it;
	}

	public Map<List<Vehicle>,String> getQueues() {
		return queues;
	}

	public void setQueues(Map<List<Vehicle>,String> queues) {
		this.queues = queues;
	}
	public void print() {
		System.out.println("Junction");
		System.out.println("id "+ id);
		
	}
}
