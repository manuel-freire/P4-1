package tp.model.simulatedObjects;

import java.util.List;

public class Vehicle {
	private int actualVel, maxVel, location, kilometrage,maxBreakTime;
	private List<Road> itinerary;
	private Road actualRoad;
	private String id;
	private int brokenTime;
	
	public Road getActualRoad() {
		return actualRoad;
	}
	public void setActualRoad(Road actualRoad) {
		this.actualRoad = actualRoad;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public Vehicle(int maxVel, int maxBreakTime, List<Road> itinerary, String id) {
		this.maxVel = maxVel;
		this.itinerary = itinerary;
		this.maxBreakTime = maxBreakTime;
		this.id = id;
	}
	public void advance() {
		if(brokenTime <= 0) {
			location += actualVel;
			if(location>actualRoad.getLength())
				location = actualRoad.getLength();
			actualRoad.getEndJunction().enterVehicle(this);
		}else
			brokenTime--;
	}
	public int getActualVel() {
		return actualVel;
	}
	public void setActualVel(int actualVel) {
		this.actualVel = actualVel;
	}
	public int getBrokenTime() {
		return brokenTime;
	}
	public void setBrokenTime(int brokenTime) {
		this.brokenTime = brokenTime;
	}
	public boolean isOutOfOrder() {
		return brokenTime>0;
	}
	public String getID() {
		return id;
	}
	public void advanceToNextRoad() {
		itinerary.remove(0);
		Road r = itinerary.get(0);
		actualRoad = r;
		r.entersVehicle(this);
	}
	public String generateReport(int time) {
		return "[Vehicle report]\n id = " + id + "\n time = " + time + "\n kilometrage = " + kilometrage + "\n (" + actualRoad.getID() + "," + location + ")";
	}
	public int getLocation() {
		return location;
	}
	public int getMaxBreakTime() {
		return maxBreakTime;
	}
	public int getMaxVel() {
		return maxVel;
	}
}
