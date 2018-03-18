package main.model.simulatedObjects;

import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Road {
	private ArrayList<Vehicle> listvehicles = new ArrayList<>();
	private int length, max_speed;
	private String id;
	private String dest, src;
	
	/**
	 * Class constructor.
	 */
	public Road(String id, String src, String dest, int max_speed, int length) {
		this.id = id;
		this.length = length;
		this.dest = dest;
		this.setMaxSpeed(max_speed);
	}
	
	/**
	 * Returns the junction at the end of the road.
	 * @return
	 */
	public String getEndJunction() {
		return dest;
	}
	/**
	 * Returns the junction at the source of the road.
	 * @return source junction
	 */
	public String getSourceJunction() {
		return src;
	}
	/**
	 * Returns the identification of the road.
	 * @return identification
	 */
	public String getID() {
		return id;
	}
	/**
	 * Returns the length of the road.
	 * @return length of the road
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Updates the list of vehicles in the road.
	 * @param vehicle added to the ones in the road
	 */
	public void entersVehicle(Vehicle vehicle) {
		getListVehicles().add(vehicle);
	}
	/**
	 * Removes a vehicle from the list of vehicles in the road.
	 * @param vehicle
	 */
	public void exitsVehicle(Vehicle vehicle) {
		getListVehicles().remove(vehicle);
	}
	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance(TrafficSimulator sim) {
		int reduction_factor = 0;
		double base_speed = Math.min(getMaxSpeed(), Math.floorDiv(getMaxSpeed(),Math.max(getListVehicles().size(), 1)));
		for(Vehicle v : getListVehicles()) {
			v.setActualVel((int)base_speed/reduction_factor);
			v.advance(sim);
			reduction_factor += (v.isOutOfOrder()) ? 1 : 0;
		}
	}
	/**
	 * Generates a report of the status of the road.
	 * @param time current time of the simulation
	 * @return report of the status of the road
	 */
	public String generateReport(int time) {
		/*
		 * [road_report] id = r3 time = 4 state = (v2,80),(v3,67) 
		 */
		String state = new String();
		for(Vehicle v : getListVehicles())
			state += "(" + v.getID() + "," + v.getLocation() + ")";
		return "[road_report]\n id = " + id + "\n time = " + time + "\n state" + state;
	}

	public int getMaxSpeed() {
		return max_speed;
	}

	public void setMaxSpeed(int max_speed) {
		this.max_speed = max_speed;
	}

	public ArrayList<Vehicle> getListVehicles() {
		return listvehicles;
	}

	public void setListVehicles(ArrayList<Vehicle> listvehicles) {
		this.listvehicles = listvehicles;
	}
}
