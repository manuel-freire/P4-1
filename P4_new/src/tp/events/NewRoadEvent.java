package tp.events;

import java.io.BufferedReader;

public class NewRoadEvent extends Event{
	String src, dest;
	int max_speed, length;
	public NewRoadEvent() {
		eventId="new_road";
	}
	@Override
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
