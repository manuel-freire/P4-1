package tp.model.simulatedObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import tp.model.TrafficSimulator;

public class Junction {
	private Map<List<Vehicle>,Road> queues;
	private Iterator<Map.Entry<List<Vehicle>,Road>> it;
	private String id;
	
	/**
	 * Class constructor.
	 * @param id identification of the junction
	 * @param incoming roads that end in the junction
	 */
	public Junction(String id, List<Road> incoming) {
		for(Road r : incoming)
			queues.put(new ArrayList<Vehicle>(), r);
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
	 * Enters a vehicle in the junction.
	 * @param vehicle vehicle to be included
	 * @param r road it came from
	 */
	public void enterVehicle(Vehicle vehicle, Road r) {
		for (Map.Entry<List<Vehicle>,Road> entry : queues.entrySet())
			if(entry.getValue() == r)
				entry.getKey().add(vehicle);
	}
	/**
	 * Moves the vehicles that can advance to the next road.
	 */
	public void advance(TrafficSimulator sim) {
		if(!it.hasNext())
			it = queues.entrySet().iterator();
		try {
		Map.Entry<List<Vehicle>,Road> pair = it.next();
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
	public void generateReport(int time) {
		/*
		 * [junction_report] 
		 * id = j2 
		 * time = 5 
		 * queues = (r1,red,[]),(r2,green,[v3,v2,v5]),(r3,red,[v1,v4]) 
		 */
		String queuesString = new String();
		Iterator<Map.Entry<List<Vehicle>,Road>> it = queues.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<List<Vehicle>,Road> entry = it.next();
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
		System.out.println("[junction_report]\nid = " + id + "\n time = " + time + "\n queues = " + queuesString);
	}

}
