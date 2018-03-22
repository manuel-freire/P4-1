package main.model.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import es.ucm.fdi.ini.IniSection;
import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;

public class NewJunctionEvent extends Event {
	private String junctionId;
	private String eventId;
	private int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public NewJunctionEvent() {
		eventId="new_junction";
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewJunctionEvent();
	} else {
		return null;
		}
	}
	public void execute(TrafficSimulator sim) {
		Junction j= new Junction(junctionId);
		sim.addJunction(j);
	}
	public void builder(IniSection sec) {
		junctionId=sec.getValue("id");
		this.time=Integer.parseInt(sec.getValue("time"));
	}
}
