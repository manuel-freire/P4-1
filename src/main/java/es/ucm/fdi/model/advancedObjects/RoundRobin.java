package main.model.advancedObjects;

import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;
import main.model.simulatedObjects.Vehicle;
/**
 * Round junction class. Extends Junction.
 * @author Borja Lozano
 */
public class RoundRobin extends Junction {
	private int max_time_slice, min_time_slice, last;
	private int interval, time_spent, passed;
	
	/**
	 * Class constructor.
	 * @param id              identification
	 * @param max_time_slice  max number of ticks one traffic light can be green
	 * @param min_time_slice  min number of ticks one traffic light can be green
	 */
	public RoundRobin(String id, int max_time_slice, int min_time_slice) {
		super(id);
		this.max_time_slice = max_time_slice;
		this.min_time_slice = min_time_slice;
		this.time_spent = -1;
	}

	/**
	 * Adds a road to the incoming roads.
	 * 
	 * @param road_id    incoming road's id
	 */
	public void addRoad(String road_id) {
		roads.add(road_id);
		queues.add(new ArrayList<Vehicle>());
		interval = max_time_slice;
	}
	public void advance(TrafficSimulator sim) {
		if(roads.isEmpty())
			return;
		
		time_spent++;
		if (time_spent == interval) {
			if (passed == time_spent)
				interval = Math.min(interval + 1, max_time_slice);
			else if (passed == 0)
				interval = Math.max(interval - 1, min_time_slice);
			time_spent = 0;
			green++;
			if (green >= roads.size())
				green = 0;
			passed = 0;
		}
		this.last = interval - time_spent;
		if(!queues.get(green).isEmpty()) {
			queues.get(green).get(0).advanceToNextRoad(sim);
			passed++;
		}
	}

	/**
	 * Generates a report of the status of the road.
	 * 
	 * @param time   current time of the simulation.
	 */
	public String generateReport(int time) {
		String queuesString = new String();
		for (int i = 0; i < roads.size(); i++) {
			queuesString += "(" + roads.get(i) + ((i == green) ?",green:" + (last) : ",red:")
					+ ",[";
			for (int t = 0; t < queues.get(i).size(); t++) {
				queuesString += queues.get(i).get(t).getID();
				if (t < queues.get(i).size() - 1)
					queuesString += ",";
			}
			queuesString += "])";
			if (i < roads.size() - 1)
				queuesString += ',';
		}
		return "[junction_report]\nid = " + id + "\ntime = " + time + "\nqueues = " + queuesString + "\ntype = rr\n";
	}
}
