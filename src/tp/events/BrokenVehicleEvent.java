package tp.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class BrokenVehicleEvent extends Event{
	private int duration, time;
	private ArrayList<String> vehicles;
	public BrokenVehicleEvent() {
		eventId="make_vehicle_faculty";
		vehicles=new ArrayList<String>();
	}
	void execute() {
		
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
			arr=arr[2].split(",");
		} catch (IOException e) {
				 e.printStackTrace();
			 }
			for (int i=0; i<arr.length; i++) {
				vehicles.add(arr[i]);
			}
		try {
			arr=reader.readLine().split(" ");
		} catch (IOException e) {
				e.printStackTrace();
		}
		duration=Integer.parseInt(arr[2]);
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
