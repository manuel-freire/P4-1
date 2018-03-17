package tp.control;

import tp.model.events.Event;
import tp.model.events.EventParser;
import java.io.*;
import java.util.ArrayList;

public class EventBuilder {
	EventParser parser = new EventParser();
	BufferedReader b;
	String fileName;
	
	public EventBuilder(String filename_) {
		this.fileName=filename_;
		FileReader fr = null;
		try {
			fr = new FileReader(filename_);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    
         this.b=new BufferedReader(fr);  
	}
	public ArrayList<Event> Builder() throws IllegalArgumentException  {
		String dataID = null;
		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			while((dataID = b.readLine()) != null){
		         Event event = null;
		         for (Event e : parser.parser) {
		        	 event=e.parser(dataID);
			         if (event!=null) {
			        	 break;
			         }
		         }
		         if (event!=null)
		        	 event.builder(b);
		         else
		        	 throw new IllegalArgumentException();
		         eventList.add(event);
		     }
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
	    return eventList;
	}
}