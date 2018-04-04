package es.ucm.fdi.model.simulatedObjects;

import java.util.ArrayList;

import es.ucm.fdi.model.TrafficSimulator;
/**
 * Junction class.
 * @author Borja Lozano
 */
public class Junction {
	protected ArrayList<ArrayList<Vehicle>> queues;
	protected ArrayList<String> roads;
	protected int green;
	protected String id;
	
	/**
	 * Class constructor.
	 * @param id identification of the junction
	 */
	public Junction(String id) {
		queues = new ArrayList<ArrayList<Vehicle>>();
		roads = new ArrayList<String>();
		green = 0;
		this.id = id;
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
		roads.add(road_id);
		queues.add(new ArrayList<Vehicle>());
	}
	/**
	 * Enters a vehicle in the junction.
	 * @param vehicle vehicle to be included
	 * @param road_id identification of the road it came from
	 */
	public void enterVehicle(Vehicle vehicle, String road_id) {
		int i = roads.indexOf(road_id);
		queues.get(i).add(vehicle);
	}
	/**
	 * Moves the vehicles that can advance to the next road.
	 * @param sim simulator
	 */
	public void advance(TrafficSimulator sim) {
		green++;
		if(green >= roads.size())
			green = 0;
		if(queues.size()!=0) {
			if(!queues.get(green).isEmpty()) {
				Vehicle v = queues.get(green).get(0);
					v.advanceToNextRoad(sim);
					v.setActualVel(0);
					while(queues.get(green).remove(v)) {} 
			}
		}
	}
	/**
	 * Generates a report of the status of the road.
	 * @param time current time of the simulation.
	 * @return report
	 */
	public String generateReport(int time) {
		/*
		 * [junction_report] 
		 * id = j2 
		 * time = 5 
		 * queues = (r1,red,[]),(r2,green,[v3,v2,v5]),(r3,red,[v1,v4]) 
		 */
		String queuesString = new String();
		for(int i = 0; i < roads.size(); i++) {
			queuesString += "(" + roads.get(i) + ((i == getPrev(green))? ",green" : ",red") + ",[";
			for(int t = 0; t < queues.get(i).size(); t++) {
				queuesString += queues.get(i).get(t).getID();
				if(t < queues.get(i).size()-1)
					queuesString += ",";
			}
			queuesString += "])";
			if(i < roads.size()-1)
				queuesString += ',';
		}
		return "[junction_report]\nid = " + id + "\ntime = " + time + "\nqueues = " + queuesString + "\n";
	}
	protected int getPrev(int i) {
		if(i == 0)
			return this.queues.size()-1;
		else
			return i-1;
	}
}
