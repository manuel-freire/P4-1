package main.model.advancedObjects;

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
		double base_speed = Math.min(getMaxSpeed(), Math.floorDiv(getMaxSpeed()*lanes,Math.max(getListVehicles().size(), 1)+1));
		for(Vehicle v : getListVehicles()) {
			v.setActualVel((int)base_speed/reduction_factor);
			v.advance(sim);
			reduction_factor += (v.isOutOfOrder()) ? 1 : 0;
		}
	}
	public void print() {
		System.out.println("lane");
		System.out.println("id "+ id);
		
	}
}
