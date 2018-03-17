package tp.model.events;

import java.io.BufferedReader;
import java.io.IOException;

import tp.model.TrafficSimulator;
import tp.model.simulatedObjects.Road;

public class NewRoadEvent extends Event{
	String src, dest, id;
	int max_speed, length;
	public NewRoadEvent() {
		eventId="new_road";
	}
	@Override
	public void execute(TrafficSimulator sim) {
		Road r = new Road();
		sim.addRoad(r);
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
		setTime(Integer.parseInt(arr[2]));
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
		src=arr[2];
		try {
			arr=reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		dest=arr[2];
		try {
			arr=reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		max_speed=Integer.parseInt(arr[2]);
		try {
			arr=reader.readLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		length=Integer.parseInt(arr[2]);
	}
	public void print() {//Only for testing purposes
		System.out.println(eventId);
		System.out.print("Src, "); System.out.println(src);
		System.out.print("Dest, "); System.out.println(dest);
		System.out.print("Id, "); System.out.println(id);
		System.out.print("Max_speed, "); System.out.println(max_speed);
		System.out.print("Length, "); System.out.println(length);
		System.out.print("Time, "); System.out.println(getTime());
	}
}
