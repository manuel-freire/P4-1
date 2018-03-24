package main.model.advancedObjects;

import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;
import main.model.simulatedObjects.Vehicle;

public class MostCrowded extends Junction{
	private int interval, time_spent;
	private int last;
	public MostCrowded(String id) {
		super(id);
		this.time_spent = -1;
		this.green = -1;
	}
	
	public void advance(TrafficSimulator sim) {
		if(roads.isEmpty())
			return;
		
		time_spent++;
		if(time_spent == interval) {
			int index = 0, max = -1;
			for(int i = 0; i < queues.size() ; i++)
				if(i != green && queues.get(i).size()>max) {
					index = i;
					max = queues.get(i).size();
				}
			green = index;
			this.interval = Math.max(max/2, 1);
			last = this.interval;
			this.time_spent = 0;
		}
		this.last = this.interval - this.time_spent;
		if(!queues.get(green).isEmpty())
			queues.get(green).get(0).advanceToNextRoad(sim);
	}
	/**
	 * Adds a road to the incoming roads.
	 * 
	 * @param road_id    incoming road's id
	 */
	public void addRoad(String road_id) {
		roads.add(road_id);
		queues.add(new ArrayList<Vehicle>());
	}
	/**
	 * Generates a report of the status of the road.
	 * 
	 * @param time current time of the simulation.
	 */
	public String generateReport(int time) {
		String queuesString = new String();
		for(int i = 0; i < roads.size(); i++) {
			queuesString += "(" + roads.get(i) + ((i == green)? ",green:" + last  : ",red")  + ",[";
			for(int t = 0; t < queues.get(i).size(); t++) {
				queuesString += queues.get(i).get(t).getID();
				if(t < queues.get(i).size()-1)
					queuesString += ",";
			}
			queuesString += "])";
			if(i < roads.size()-1)
				queuesString += ',';
		}
		return "[junction_report]\nid = " + id + "\ntime = " + time + "\nqueues = " + queuesString + "\ntype = mc\n";
	}
}
