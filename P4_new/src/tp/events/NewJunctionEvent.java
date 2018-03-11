package tp.events;

import java.io.BufferedReader;

public class NewJunctionEvent extends Event {
	public NewJunctionEvent() {
		eventId="new_junction";
	}
	@Override
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
		
	}

}
