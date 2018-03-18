package main.model.events;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;

import main.model.TrafficSimulator;
import main.model.simulatedObjects.Vehicle;

public class NewVehicleEvent extends Event{
	private String id;
	private int max_speed, max_breakTime;
	private ArrayList<String> it;
	
	public NewVehicleEvent() {
		eventId="[new_vehicle]";
		it=new ArrayList<String>();
	}
	public NewVehicleEvent(String id, int max_speed, int max_breakTime, ArrayList<String> it) { // For testing purposes
		eventId="[new_vehicle]";
		this.it = it;
		this.id = id;
		this.max_breakTime = max_breakTime;
		this.max_speed = max_speed;
	}
	public void execute(TrafficSimulator sim) {
		Vehicle v = new Vehicle(max_speed,max_breakTime,it,id);
		sim.addVehicle(v);
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return this;
	} else {
		return null;
		}
	}
	public void builder(BufferedReader reader) {
		String[]arr = null;
		try {
			arr=reader.readLine().split(" ");
			setTime(Integer.parseInt(arr[2]));
			arr=reader.readLine().split(" ");
			id=arr[2];
			arr=reader.readLine().split(" ");
		   	max_speed=Integer.parseInt(arr[2]);
			arr=reader.readLine().split(" ");
			arr=arr[2].split(",");
			for (int i=0; i<arr.length; i++)
				it.add(arr[i]);
		}catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public void print() {  //Only for testing purposes
		System.out.println(eventId);
		System.out.print("Max_speed, "); System.out.println(max_speed);
		System.out.print("Time, "); System.out.println(getTime());
		System.out.print("Id, "); System.out.println(id);
		for (int i=0;i<it.size(); i++) {
			System.out.println(it.get(i));
		}
	}
}
