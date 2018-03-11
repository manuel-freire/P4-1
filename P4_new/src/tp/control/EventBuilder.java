package tp.control;

import tp.events.*;
import java.io.*;
import java.util.ArrayList;

public class EventBuilder {
	EventParser parser = new EventParser();
	BufferedReader b;
	String fileName;
	public Event Builder() {
		String dataId = null;
		FileReader fr = null;
			try {
				fr = new FileReader("test.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}    
	         BufferedReader br=new BufferedReader(fr);   
	         try {
				dataId=br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	         Event finalEvent = null;
	         for (Event e : parser.parser) {
	        	 finalEvent=e.parser(dataId);
	         }
	         if (finalEvent!=null) {
	        	 finalEvent.builder(br);
	         } else {
	        	 //Throw invalid format file exeption
	         }
	    	return finalEvent;
	}
	public EventsList finalBuilder() {
		EventsList t= new EventsList();
		return t;
	}

}
