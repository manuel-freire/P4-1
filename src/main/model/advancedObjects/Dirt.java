package main.model.advancedObjects;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Road;
import main.model.simulatedObjects.Vehicle;

public class Dirt extends Road{

	public Dirt(String id, String src, String dest, int max_speed, int length) {
		super(id, src, dest, max_speed, length);
	}
	/**
	 * Updates the velocity of every vehicle in it and calls their advance method.
	 */
	public void advance(TrafficSimulator sim) {
		int reduction_factor = 1;
		for(Vehicle v : getListVehicles()) 
			if(v.isOutOfOrder())
				reduction_factor++;
		double base_speed = getMaxSpeed();
		for(Vehicle v : getListVehicles()) {
			v.setActualVel((int)base_speed/reduction_factor);
			v.advance(sim);
			reduction_factor += (v.isOutOfOrder()) ? 1 : 0;
		}
	}
	public void print() {
		System.out.println("dirt");
		System.out.println("id "+ id);
		
	}
}
