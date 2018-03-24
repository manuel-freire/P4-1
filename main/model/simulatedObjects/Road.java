package main.model.simulatedObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Road {
	protected ArrayList<Vehicle> listVehicles = new ArrayList<>();
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
	 * 
	 * @return
	 */
	public String getEndJunction() {
		return dest;
	}

	/**
	 * Returns the junction at the source of the road.
	 * 
	 * @return source junction
	 */
	public String getSourceJunction() {
		return src;
	}

	/**
	 * Returns the identification of the road.
	 * 
	 * @return identification
	 */
	public String getID() {
		return id;
	}

	/**
	 * Returns the length of the road.
	 * 
	 * @return length of the road
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Updates the list of vehicles in the road.
	 * 
	 * @param vehicle
	 *            added to the ones in the road
	 */
	public void entersVehicle(Vehicle vehicle) {
		listVehicles.add(vehicle);
	}

	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance(TrafficSimulator sim) {
		int reduction_factor = 1;
		int base_speed = Math.min(max_speed,max_speed/ Math.max(listVehicles.size(), 1)+1);
		Collections.sort(listVehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle v2, Vehicle v1) {
				return new Integer(v1.getLocation()).compareTo(v2.getLocation());
			}
		});
		for (Vehicle v : listVehicles) {
			if(v.isOutOfOrder())
				reduction_factor = 2;
			v.setActualVel((int) base_speed / reduction_factor);
			v.advance(sim);
		}
	}

	/**
	 * Generates a report of the status of the road.
	 * 
	 * @param time current time of the simulation
	 * @return report of the status of the road
	 */
	public String generateReport(int time) {
		/*
		 * [road_report] id = r3 time = 4 state = (v2,80),(v3,67)
		 */
		String state = new String();
		for (int i = 0; i < listVehicles.size(); i++) {
			Vehicle v = listVehicles.get(i);
			state += "(" + v.getID() + "," + v.getLocation() + ")";
			if (i < listVehicles.size() - 1)
				state += ',';
		}
		return "[road_report]\nid = " + id + "\ntime = " + time + "\nstate = " + state + "\n";
	}

	public int getMaxSpeed() {
		return max_speed;
	}

	public void setMaxSpeed(int max_speed) {
		this.max_speed = max_speed;
	}

	public ArrayList<Vehicle> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(ArrayList<Vehicle> listvehicles) {
		this.listVehicles = listvehicles;
	}

	public void print() {
		System.out.println("road");
		System.out.println("id " + id);
	}
	
	public void exitVehicle(Vehicle vehicle) {
		listVehicles.remove(vehicle);
		
	}
}
