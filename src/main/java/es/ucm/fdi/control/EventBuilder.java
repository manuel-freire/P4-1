package es.ucm.fdi.control;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.ini.*;
import es.ucm.fdi.model.events.*;

/**
 * Builds the event list.
 * @author Javier Navalon
 */
public class EventBuilder {
	Event[] parser= {new NewJunctionEvent(), new NewRoadEvent(), new NewVehicleEvent(), new BrokenVehicleEvent()};
	String fileName;
	Ini ini;
	/**
	 * Class constructor
	 * @param filename path to the input file
	 */
	public EventBuilder(String filename) {
		this.fileName=filename;
		try {
			this.ini = new Ini(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Reads from the file and returns all the events recorded.
	 * @return array of events read from the file
	 * @throws IllegalArgumentException if the parameters or format of the events were incorrect.
	 */
	public ArrayList<Event> Builder() throws IllegalArgumentException  {
		ArrayList<Event> eventList = new ArrayList<Event>();
		List<IniSection>sections = new ArrayList<IniSection>();
		sections=ini.getSections();
		for (int j=0; j<sections.size(); j++) {
			Event auxEvent = null;
			for (Event e:parser) {
				auxEvent=e.parser(sections.get(j).getTag());
				if (auxEvent!=null) {
					break;
				}
			}
				if (auxEvent!=null) {
					auxEvent.builder(sections.get(j));
					eventList.add(auxEvent);
				}
		}
	    return eventList;
	}
}