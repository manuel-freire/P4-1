package main.model.advancedObjects;

import java.util.ArrayList;
import java.util.Random;
import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Car extends Vehicle {
	private int resistance, since_fault,max_fault_duration;
	private double faulty_probability;
	private Random rand; 
	/**
	 * Class constructor
	 * @param id                 identification of the vehicle
	 * @param itinerary          collection of junctions to go by
	 * @param max_speed          maximum speed of the vehicle
	 * @param max_fault_duration maximum time the vehicle can be out of order
	 * @param resistance         resistance in km to start breaking down after being repaired
	 * @param seed               seed of the random number generator
	 * @param fault_probability  probability to break down
	 */
	public Car(String id, ArrayList<String> itinerary, int max_speed, int max_fault_duration, int resistance, int seed, double faulty_probability) {
		super(max_speed,itinerary,id);
		this.faulty_probability = faulty_probability;
		this.max_fault_duration = max_fault_duration;
		this.resistance = resistance;
		this.since_fault = 0;
		if(seed!=0)
			rand = new Random(seed);
		else
			rand = new Random(System.currentTimeMillis());
	} 
	/**
	 * Advances the vehicle. If it has advanced more than the resistance since being repaired it might break down.
	 */
	public void advance(TrafficSimulator sim) {
			if(brokenTime <= 0) {
				if(since_fault >= resistance && rand.nextDouble() < faulty_probability)
					setFaultTime(rand.nextInt(max_fault_duration+1));
				else {
					int km = getLocation();
					setLocation(getLocation() + getActualVel());
					if(getLocation()>=getActualRoad().getLength()) {
						setLocation(getActualRoad().getLength());
						setActualVel(0);
						if(!isWaiting()) {
							setWaiting(true);
							sim.getJunction(getActualRoad().getEndJunction()).enterVehicle(this,getActualRoad().getID());
						}
					}
					setKilometrage(getKilometrage() + (getLocation()-km));
				}
			}else {
				brokenTime--;
				setActualVel(0);
			}
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	@Override
	public String generateReport(int time) {
		String loc = isArrived() ? "arrived" : "(" + getActualRoad().getID() + "," + getLocation() + ")";
		return "[vehicle_report]\nid = " + getId() + "\ntime = " + time + "\ntype = car\nspeed = " + this.getActualVel() + "\nkilometrage = " + getKilometrage() + "\nfaulty = " + getBrokenTime() + "\nlocation = " + loc + "\n";
	}
}
