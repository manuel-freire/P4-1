package tp.control.events;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp.model.TrafficSimulator;
import tp.model.simulatedObjects.Road;
import tp.model.simulatedObjects.Vehicle;
public class NewVehicleEvent extends Event{
	String id;
	private int max_speed, time;
	private List<Road> it;
	public NewVehicleEvent() {
		eventId="new_vehicle";
		it=new List<Road>();
	}
	public void execute(TrafficSimulator sim) {
		Vehicle v = new Vehicle();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		time=Integer.parseInt(arr[2]);
		try {
			arr=reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		id=arr[2];
	    try {
		 arr=reader.readLine().split(" ");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	   	max_speed=Integer.parseInt(arr[2]);
		try {
		arr=reader.readLine().split(" ");
		arr=arr[2].split(",");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		for (int i=0; i<arr.length; i++) {
			it.add(arr[i]);
		}
	}
	public void print() {  //Only for testing purposes
		System.out.println(eventId);
		System.out.print("Max_speed, "); System.out.println(max_speed);
		System.out.print("Time, "); System.out.println(time);
		System.out.print("Id, "); System.out.println(id);
		for (int i=0;i<it.size(); i++) {
			System.out.println(it.get(i));
		}
	}
}
