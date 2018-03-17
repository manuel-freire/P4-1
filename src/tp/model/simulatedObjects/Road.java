package tp.model.simulatedObjects;

import java.util.ArrayList;
import tp.model.simulatedObjects.Vehicle;

public class Road {
	private ArrayList<Vehicle> listvehicles = new ArrayList<>();
	private int length, maxVel;
	private String id;
	private Junction end;
	
	/**
	 * Class constructor.
	 */
	public Road(String id, Junction fin, int maxVel, int length) {
		this.id = id;
		this.length = length;
		this.end = fin;
		this.maxVel = maxVel;
	}
	
	/**
	 * Returns the junction at the end of the road.
	 * @return
	 */
	public Junction getEndJunction() {
		return end;
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
		listvehicles.add(vehicle);
	}
	/**
	 * Removes a vehicle from the list of vehicles in the road.
	 * @param vehicle
	 */
	public void exitsVehicle(Vehicle vehicle) {
		listvehicles.remove(vehicle);
	}
	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance() {
		int factorReduction = 0;
		double baseVel = Math.min(maxVel, Math.floorDiv(maxVel,Math.max(listvehicles.size(), 1)));
		for(int i = listvehicles.size()-1; i >= 0 ; i-- ) {
			listvehicles.get(i).setActualVel((int)baseVel/factorReduction);
			listvehicles.get(i).advance();
			factorReduction += (listvehicles.get(i).isOutOfOrder()) ? 1 : 0;
		}
	}
	/**
	 * Generates a report of the status of the road.
	 * @param time current time of the simulation
	 * @return report of the status of the road
	 */
	public String generateReport(int time) {
		String state = new String();
		for(Vehicle v : listvehicles)
			state += "(" + v.getID() + "," + v.getLocation() + ")";
		return "[road_report]\n id = " + id + "\n time = " + time + "\n state" + state;

		/*
		 * [road_report] id = r3 time = 4 state = (v2,80),(v3,67) 
		 */
	}
}
