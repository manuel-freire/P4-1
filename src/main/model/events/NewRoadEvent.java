package main.model.events;

import java.io.BufferedReader;
import java.io.IOException;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;
import main.model.advancedObjects.Dirt;
import main.model.advancedObjects.Lanes;
import main.model.simulatedObjects.Road;

public class NewRoadEvent extends Event{
	String src, dest, id, type;
	int max_speed, length, lanes;
	private String eventId;
	private int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public NewRoadEvent() {
		eventId="new_road";
	}
	@Override
	public void execute(TrafficSimulator sim) {
		if (type=="lanes") {
			Lanes ln = new Lanes(id, src, dest, max_speed, length, lanes);
			sim.addRoad(ln);
		} else if(type=="dirt"){
			Dirt dr = new Dirt(id, src, dest, max_speed, length);
			sim.addRoad(dr);
		} else {
			Road r = new Road(id, src, dest, max_speed, length);
			sim.addRoad(r);
		}
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewRoadEvent();
	} else {
		return null;
		}
	}
	public void builder(IniSection sec) {
		this.id=sec.getValue("id");
		this.src=sec.getValue("src");
		this.dest=sec.getValue("dest");
		this.time=Integer.parseInt(sec.getValue("time"));
		this.length=Integer.parseInt(sec.getValue("length"));
		this.max_speed=Integer.parseInt(sec.getValue("max_speed"));
		if (sec.getValue("type")=="lanes") {
			this.lanes=Integer.parseInt(sec.getValue("lanes"));
		} else if(sec.getValue("type")=="dirt") {
		} 
	}
	public void print() {//Only for testing purposes
		System.out.println("---");
		System.out.println("ROAD");
		if (type=="lanes") {
			System.out.println("lanes");
			System.out.println("id "+id);
			System.out.println("src "+src);
			System.out.println("dest "+dest);
			System.out.println("maxspeed "+max_speed);
			System.out.println("length "+length);
			System.out.println("lanes "+lanes);
		} else {
			System.out.println("dirt ");
			System.out.println("id "+id);
			System.out.println("src "+src);
			System.out.println("dest "+dest);
			System.out.println("maxspeed "+max_speed);
			System.out.println("length "+length);
		}
	}
}
