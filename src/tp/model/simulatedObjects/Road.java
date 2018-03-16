package tp.model.simulatedObjects;

import java.util.ArrayList;
import tp.model.simulatedObjects.Vehicle;

public class Road {
	ArrayList<Vehicle> listvehicles = new ArrayList<>();
	int length, maxVel;
	String id;
	Junction fin; // ini;
	
	public void entersVehicle(Vehicle vehicles) {
		listvehicles.add(vehicles);
	}
	public void exitsVehicle(Vehicle vehicles) {
		fin.enterVehicle(vehicles);
		listvehicles.remove(vehicles);
	}
	public void advance() {
		int factorReduction = 0;
		double baseVel = Math.min(maxVel, Math.floorDiv(maxVel,Math.max(listvehicles.size(), 1)));
		for(int i = listvehicles.size()-1; i >= 0 ; i-- ) {
			listvehicles.get(i).setActualVel((int)baseVel/factorReduction);
			listvehicles.get(i).advance();
			factorReduction += (listvehicles.get(i).isOutOfOrder()) ? 1 : 0;
		}
	}
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String generateReport(int time) {
		String state = new String();
		for(Vehicle v : listvehicles)
			state += "(" + v.getID() + "," + v.getLocation() + ")";
		return "[road_report]\n id = " + id + "\n time = " + time + "\n state" + state;

		/*
		 * [road_report] id = r3 time = 4 state = (v2,80),(v3,67) 
		 */
	}
	public Junction getEndJunction() {
		return fin;
	}
}
