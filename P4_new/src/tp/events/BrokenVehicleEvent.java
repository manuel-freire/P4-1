package tp.events;

import java.io.BufferedReader;
import java.util.ArrayList;

public class BrokenVehicleEvent extends Event{
	int duration;
	public BrokenVehicleEvent() {
		eventId="make_vehicle_faculty";
	}
	private ArrayList<String>vehicles;
	void execute() {
		// TODO Auto-generated method stub
		
	}
	Event parser(String id){
		if (eventId.equals(id)) {
		return this;
	} else {
		return null;
		}
	}
	public void builder(BufferedReader reader) {
			
		}
}
