package main.model.advancedObjects;

import java.util.Collections;
import java.util.Comparator;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Lanes extends Road{
	private int lanes;
	public Lanes(String id, String src, String dest, int max_speed, int length, int lanes) {
		super(id, src, dest, max_speed, length);
		this.lanes = lanes;
	}
	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance(TrafficSimulator sim) {
		int reduction_factor = 0;
		int i = 0;
		for(Vehicle v : getListVehicles()) 
			if(v.isOutOfOrder())
				i++;
		if(i < lanes)
			reduction_factor = 1;
		else
			reduction_factor = 2;
		Collections.sort(listVehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle v2, Vehicle v1) {
				return new Integer(v1.getLocation()).compareTo(v2.getLocation());
			}
		});
		double base_speed = Math.min(getMaxSpeed(), getMaxSpeed()*lanes/Math.max(getListVehicles().size(), 1)+1);
		for(Vehicle v : getListVehicles()) {
			v.setActualVel((int)base_speed/reduction_factor);
			v.advance(sim);
			reduction_factor += (v.isOutOfOrder()) ? 1 : 0;
		}
	}
	/**
	 * Generates a report of the status of the lanes road.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	@Override
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
		return "[road_report]\nid = " + id + "\ntime = " + time + "\ntype = lanes\nstate = " + state + "\n";
	}
}
