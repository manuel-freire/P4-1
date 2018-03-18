package main.model.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import main.model.TrafficSimulator;

public class BrokenVehicleEvent extends Event{
	private int duration, time;
	private ArrayList<String> vehicles;
	
	public BrokenVehicleEvent() {
		eventId="[make_vehicle_faculty]";
		vehicles=new ArrayList<String>();
	}
	public BrokenVehicleEvent(ArrayList<String> vehicles_id,int dur) { //Only for testing purposes
		eventId="[make_vehicle_faculty]";
		vehicles=vehicles_id;
		this.duration = dur;
	}
	public void execute(TrafficSimulator sim) {
		sim.breakVehicles(vehicles,duration);
	}
	public Event parser(String id){
		if (eventId.equals(id))
			return this;
		else
			return null;
	}
	public void builder(BufferedReader reader) {
		String[]arr = null;
		try {
			arr=reader.readLine().split(" ");
			time=Integer.parseInt(arr[2]);
			arr=reader.readLine().split(" ");
			arr=arr[2].split(",");
			for (int i=0; i<arr.length; i++)
				vehicles.add(arr[i]);
			arr=reader.readLine().split(" ");
			duration=Integer.parseInt(arr[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void print() {  //Only for testing purposes
		System.out.println(eventId);
		System.out.print("Duration, "); System.out.println(duration);
		System.out.print("Time, "); System.out.println(time);
		for (int i=0; i<vehicles.size(); i++) {
			System.out.println(vehicles.get(i));
		}
	}
}
