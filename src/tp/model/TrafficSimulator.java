package tp.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import tp.model.simulatedObjects.*;
import tp.model.events.Event;

public class TrafficSimulator {
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Event> events;
	int contadorTiempo, pasosSimulacion;
	
	public TrafficSimulator(int pasosSimulacion) {
		contadorTiempo = 0;
		this.pasosSimulacion = pasosSimulacion;
	}
	/**
	 * Starts the simulation.
	 */
	public void execute() {
		int limiteTiempo = this.contadorTiempo + pasosSimulacion - 1; 
		while (this.contadorTiempo <= limiteTiempo) { 
			for(Event e: events)
				if(e.getTime() <= contadorTiempo)
					e.execute(this);
			for(Road r : roads)
				r.advance(this);
			for(Junction j : junctions)
				j.advance(this);
			contadorTiempo++; 
			
			for(Road r : roads)
				r.generateReport(contadorTiempo);
			for(Junction j : junctions)
				j.generateReport(contadorTiempo);
			for(Vehicle v : vehicles)
				v.generateReport(contadorTiempo);
			
			contadorTiempo++;
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
