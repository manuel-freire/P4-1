package main.model.simulatedObjects;

import java.util.List;
import java.util.NoSuchElementException;

import main.model.TrafficSimulator;

public class Vehicle {
	private int actualVel, maxVel, location, kilometrage,max_fault_duration;
	private List<String> itinerary;
	private Road actualRoad;
	private String id;
	protected int brokenTime;
	
	/**
	 * Constructs a vehicle with its desired behaviour.
	 * @param maxVel       maximum velocity it can have
	 * @param maxBreakTime maximum time it can be broken down
	 * @param itinerary    collection of junctions it has to go through
	 * @param id           identification of the vehicle
	 */
	public Vehicle(int maxVel, int maxBreakTime, List<String> itinerary, String id) {
		this.maxVel = maxVel;
		this.itinerary = itinerary;
		this.max_fault_duration = maxBreakTime;
		this.setId(id);
	}
	
	/**
	 * Returns the road that is travelling through.
	 * 
	 * @return road that the vehicle is in
	 */
	public Road getActualRoad() {
		return actualRoad;
	}
	/**
	 * Returns the velocity of the vehicle.
	 * @return velocity of the vehicle
	 */
	public int getActualVel() {
		return actualVel;
	}
	/**
	 * Returns the location relative to the road it is in.
	 * @return location of the vehicle
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * Returns the maximum time it can be broken down.
	 * @return maximum time to be broken down.
	 */
	public int getMaxBreakTime() {
		return max_fault_duration;
	}
	/**
	 * Returns the maximum velocity of the vehicle.
	 * @return maximum velocity
	 */
	public int getMaxVel() {
		return maxVel;
	}
	/**
	 * Returns the time till it is no longer out of order.
	 * @return the time till it can move again.
	 */
	public int getBrokenTime() {
		return brokenTime;
	}
	/**
	 * Returns the identification of the vehicle.
	 * @return identification
	 */
	public String getID() {
		return getId();
	}
	/**
	 * Returns if the vehicle is out of order.
	 * @return whether the vehicle is broken down.
	 */
	public boolean isOutOfOrder() {
		return brokenTime>0;
	}
	
	/**
	 * Sets the time it will be out of order.
	 * @param fault_time time to be broken down.
	 */
	public void setFaultTime(int fault_time) {
		if(fault_time>this.max_fault_duration)
			this.brokenTime = max_fault_duration;
		else
			this.brokenTime = fault_time;
	}
	/**
	 * Updates the velocity of the vehicle.
	 * @param actualVel velocity of the vehicle.
	 */
	public void setActualVel(int actualVel) {
		this.actualVel = actualVel;
	}
	/**
	 * Updates the road that the vehicle is travelling through.
	 * @param actualRoad road that it is in
	 */
	public void setActualRoad(Road actualRoad) {
		this.actualRoad = actualRoad;
	}
	/**
	 * Updates the location of the vehicle in the road.
	 * @param location distance travelled in the road
	 */
	public void setLocation(int location) {
		this.location = location;
	}
	
	/**
	 * Updates the vehicle's position in the road. If it has reached the end of the road it enters the junction and exits from the road.
	 */
	public void advance(TrafficSimulator sim) {
		if(brokenTime <= 0) {
			location += actualVel;
			if(location>actualRoad.getLength())
				location = actualRoad.getLength();
			try {
				sim.getJunction(actualRoad.getEndJunction()).enterVehicle(this,actualRoad.getID());
			}catch(NoSuchElementException e) {
				System.out.println(e.getMessage());
			}
			actualRoad.exitsVehicle(this);
		}else
			brokenTime--;
	}
	/**
	 * Advances to the next road.
	 */
	public void advanceToNextRoad(TrafficSimulator sim) {
		actualRoad.exitsVehicle(this);
		itinerary.remove(0);
		try {
			Road r = sim.getRoad(itinerary.get(0),itinerary.get(1));
			actualRoad = r;
			r.entersVehicle(this);
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("Vehicle "+getId()+" should have reached its destination.");
		}
	}
	/**
	 * Generates a report of the status of the vehicle.
	 * @param time time passed from the start of the simulation
	 * @return String of the report
	 */
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + getId() + "\n time = " + time + "\n kilometrage = " + getKilometrage() + "\n (" + actualRoad.getID() + "," + location + ")";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}
}
