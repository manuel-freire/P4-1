package tp.events;
import java.io.BufferedReader;
import java.util.ArrayList;
public class NewVehicleEvent extends Event{
	private int max_speed;
	private ArrayList<String>it;
	public NewVehicleEvent() {
		eventId="new_vehicle";
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
			
		}
}
