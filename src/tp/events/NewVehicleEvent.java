package tp.events;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
public class NewVehicleEvent extends Event{
	String id;
	private int max_speed, time;
	private ArrayList<String>it;
	public NewVehicleEvent() {
		eventId="new_vehicle";
		it=new ArrayList<String>();
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
