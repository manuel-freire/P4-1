package tp.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import tp.model.simulatedObjects.*;
import tp.model.events.Event;

public class TrafficSimulator {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Event> events;
	int actualTick, totalTicks;
	
	public TrafficSimulator(int ticks) {
		actualTick = 0;
		this.totalTicks = ticks;
	}
	/**
	 * Starts the simulation.
	 */
	public void execute(FileOutputStream ostream) {
		int limiteTiempo = this.actualTick + totalTicks - 1; 
		while (this.actualTick <= limiteTiempo) { 
			for(Event e: events)
				if(e.getTime() <= actualTick)
					e.execute(this);
			for(Road r : roads)
				r.advance(this);
			for(Junction j : junctions)
				j.advance(this);
			actualTick++; 
			try {
				for(Road r : roads)
					ostream.write(r.generateReport(actualTick).getBytes());
				for(Junction j : junctions)
					ostream.write(j.generateReport(actualTick).getBytes());
				for(Vehicle v : vehicles)
					ostream.write(v.generateReport(actualTick).getBytes());
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		} 
	}
	/**
	 * Returns the road that matches the source junction and destination junction
	 * @param road_id
	 * @param end_id
	 * @return road that matches the parameters
	 */
	public Road getRoad(String road_id, String end_id) {
		Road road = null;
		for(Road r: roads)
			if(r.getID()==road_id&&getJunction(r.getEndJunction()).getID()==end_id)
				road = r;
		if(road == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return road;
	}
	/**
	 * Returns the junction that matches the id.
	 * @param id
	 * @return junction that matches the id
	 * @throws NoSuchElementException
	 */
	public Junction getJunction(String id) throws NoSuchElementException {
		Junction jun = null;
		for(Junction j : junctions)
			if(j.getID() == id)
				jun = j;
		if(jun == null)
			throw new NoSuchElementException("The junction does not exist.");
		else
			return jun;
	}
	/**
	 * Add a event to the simulation
	 * @param e event to be added
	 */
	public void addEvent(Event e) {
		events.add(e);
	}
	/**
	 * Add a road to the simulation
	 * @param r road to be added
	 */
	public void addRoad(Road r) {
		roads.add(r);
	}
	/**
	 * Add a vehicle to the simulation
	 * @param v vehicle to be added
	 */
	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}
	/**
	 * Add a junction to the simulation
	 * @param j junction to be added
	 */
	public void addJunction(Junction j) {
		junctions.add(j);
	}
}
