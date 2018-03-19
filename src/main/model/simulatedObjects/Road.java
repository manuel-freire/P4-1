package main.model.simulatedObjects;

import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Road {
	private ArrayList<Vehicle> listvehicles = new ArrayList<>();
	private int length, max_speed;
	protected String id;
	private String dest, src;
	
	/**
	 * Class constructor.
	 */
	public Road(String id, String src, String dest, int max_speed, int length) {
		this.id = id;
		this.length = length;
		this.dest = dest;
		this.src = src;
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
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance(TrafficSimulator sim) {
		int reduction_factor = 1;
		double base_speed = Math.min(getMaxSpeed(), Math.floorDiv(getMaxSpeed(),Math.max(getListVehicles().size(), 1)));
		for(int i = 0; i <getListVehicles().size(); i++) {
			reduction_factor = 1;
			for(Vehicle aux : getListVehicles())
				if(aux.isOutOfOrder() && aux.getLocation() > getListVehicles().get(i).getLocation()) {
					reduction_factor = 2;
					break;
				}
			getListVehicles().get(i).setActualVel((int)base_speed/reduction_factor);
			if(getListVehicles().get(i).advance(sim)) {
				getListVehicles().remove(i);
				i--;
			}
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
		return "[road_report]id=" + id + "time=" + time + "state" + state;
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
	public void print() {
		System.out.println("road");
		System.out.println("id "+ id);
	}
}
