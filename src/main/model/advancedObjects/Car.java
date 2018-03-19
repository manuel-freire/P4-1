package main.model.advancedObjects;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class Car extends Vehicle {
	private int resistance, since_fault;
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
		super(max_speed,max_fault_duration,itinerary,id);
		this.faulty_probability = faulty_probability;
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
			if(!isOutOfOrder() && since_fault >= resistance && rand.nextDouble() < faulty_probability)
					setFaultTime(rand.nextInt(getMaxBreakTime()+1));
			else {
				if(getBrokenTime() <= 0) {
					setLocation(getLocation() + getActualVel());
					if(getLocation()>getActualRoad().getLength())
						setLocation(getActualRoad().getLength());
					try {
						sim.getJunction(getActualRoad().getEndJunction()).enterVehicle(this,getActualRoad().getID());
					}catch(NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					getActualRoad().exitsVehicle(this);
				}else
					setFaultTime(getBrokenTime() - 1);
			}
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + getId() + "\n time = " + time + "\n kilometrage = " + getKilometrage() + "\n (" + getActualRoad().getID() + "," + getLocation() + ")"  + "/n type = car";
	}
	public void print() {
		System.out.println("car");
		System.out.println("id "+ id);
		System.out.println("Itinerary:");
		for (int i=0; i<itinerary.size(); i++) {
			System.out.print(itinerary.get(i)+ ",");
		}
		System.out.println("max_speed "+maxVel);
		System.out.println("max_fault_duration "+max_fault_duration);
	}
}
