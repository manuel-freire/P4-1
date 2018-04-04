package es.ucm.fdi.model.events;

import java.util.NoSuchElementException;

import es.ucm.fdi.ini.*;
import es.ucm.fdi.model.TrafficSimulator;
import es.ucm.fdi.model.advancedObjects.Dirt;
import es.ucm.fdi.model.advancedObjects.Lanes;
import es.ucm.fdi.model.simulatedObjects.Road;
/**
 * Class of the event that creates a new road.
 * @author Javier Navalon
 *
 */
public class NewRoadEvent extends Event{
	String src, dest, id, type;
	int max_speed, length, lanes;
	private String eventId;
	private int time;
	public int getTime() {
		return time;
	}
	public NewRoadEvent() {
		eventId="new_road";
	}
	@Override
	public void execute(TrafficSimulator sim) {
		if(type == null){
			Road r = new Road(id, src, dest, max_speed, length);
			sim.addRoad(r);
		} else if (type.equals("lanes")) {
			Lanes ln = new Lanes(id, src, dest, max_speed, length, lanes);
			sim.addRoad(ln);
		} else if(type.equals("dirt")){
			Dirt dr = new Dirt(id, src, dest, max_speed, length);
			sim.addRoad(dr);
		} else 
			throw new NoSuchElementException("The type of road " + type + " doesn't exist");
	}
	public Event parser(String id){
		if (eventId.equals(id)) {
		return new NewRoadEvent();
	} else {
		return null;
		}
	}
	public void builder(IniSection sec) {
		this.type=sec.getValue("type");
		this.id=sec.getValue("id");
		this.src=sec.getValue("src");
		this.dest=sec.getValue("dest");
		this.time=Integer.parseInt(sec.getValue("time"));
		this.length=Integer.parseInt(sec.getValue("length"));
		this.max_speed=Integer.parseInt(sec.getValue("max_speed"));
		if(type!=null)
			if (type.equals("lanes")) {
				this.lanes=Integer.parseInt(sec.getValue("lanes"));
			} else if(sec.getValue("type")=="dirt") {
			} 
	}
}
