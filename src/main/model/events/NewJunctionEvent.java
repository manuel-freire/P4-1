package main.model.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Junction;

public class NewJunctionEvent extends Event {
	private String junctionId;
	public NewJunctionEvent() {
		eventId="[new_junction]";
	}
	public NewJunctionEvent(String id) { // For testing purposes
		eventId=id;
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return this;
	} else {
		return null;
		}
	}
	public void execute(TrafficSimulator sim) {
		Junction j= new Junction(junctionId);
		sim.addJunction(j);
	}
	public void builder(BufferedReader reader) {
		String[]arr = null;
		try {
			arr=reader.readLine().split(" ");
			setTime(Integer.parseInt(arr[2]));
			arr=reader.readLine().split(" ");
			junctionId=arr[2];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void print() {  //Only for testing purposes
		System.out.print("Time, "); System.out.println(getTime());
		System.out.print("JunctionID, "); System.out.println(junctionId);
	}
}
