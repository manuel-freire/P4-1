package es.ucm.fdi.model.advancedObjects;

import java.util.Collections;
import java.util.Comparator;

import es.ucm.fdi.model.TrafficSimulator;
import es.ucm.fdi.model.simulatedObjects.Road;
import es.ucm.fdi.model.simulatedObjects.Vehicle;
/**
 * Road with multiple lanes. Extends Road.
 * @author Borja Lozano
 */
public class Lanes extends Road{
	private int lanes;
	/**
	 * Class constructor.
	 * @param id        identification
	 * @param src       source junction
	 * @param dest      destination junction
	 * @param max_speed maximum speed
	 * @param length    length of the road
	 * @param lanes     number of lanes
	 */
	public Lanes(String id, String src, String dest, int max_speed, int length, int lanes) {
		super(id, src, dest, max_speed, length);
		this.lanes = lanes;
	}
	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 * @param sim simulation
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
		double base_speed = Math.min(getMaxSpeed(), getMaxSpeed()*lanes/Math.max(getListVehicles().size(), 1)+1);
		for(Vehicle v : getListVehicles()) {
			v.setActualVel((int)base_speed/reduction_factor);
			v.advance(sim);
			reduction_factor += (v.isOutOfOrder()) ? 1 : 0;
		}
		Collections.sort(listVehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle v2, Vehicle v1) {
				return new Integer(v1.getLocation()).compareTo(v2.getLocation());
			}
		});
	}
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
