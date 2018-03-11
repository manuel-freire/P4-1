package tp.events;

import java.io.BufferedReader;
import java.io.IOException;

public class NewJunctionEvent extends Event {
	private int time;
	private String junctionId;
	public NewJunctionEvent() {
		eventId="new_junction";
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return this;
	} else {
		return null;
		}
	}
	void execute() {

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
		junctionId=arr[2];
	}
	public void print() {  //Only for testing purposes
		System.out.print("Time, "); System.out.println(time);
		System.out.print("JunctionID, "); System.out.println(junctionId);
	}
}
