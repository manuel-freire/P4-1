package tp.control;

import tp.model.events.*;

import java.io.*;
import java.util.ArrayList;

public class EventBuilder {
	Event[] parser= {new NewJunctionEvent(), new NewRoadEvent(), new NewVehicleEvent(), new BrokenVehicleEvent()};
	BufferedReader b;
	String fileName;
	
	public EventBuilder(String filename) {
		this.fileName=filename;
		FileReader fr = null;
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    
         this.b=new BufferedReader(fr);  
	}
	public ArrayList<Event> Builder() throws IllegalArgumentException  {
		String dataID = null;
		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			while((dataID = b.readLine()) != null){ // Reads a line and stores it in dataID, if it can't read any longer exits the loop
		         Event event = null;
		         for (Event e : parser) { // Tries to parse the event
		        	 event=e.parser(dataID);
			         if (event!=null) {
			        	 break;
			         }
		         }
		         if (event!=null) // Checks that the dataID was valid
		        	 event.builder(b); // Calls the event builder to read the rest of the event
		         else
		        	 throw new IllegalArgumentException();
		         eventList.add(event);
		     }
		 } catch (IOException e) {
			e.printStackTrace();
		 }
	    return eventList;
	}
}