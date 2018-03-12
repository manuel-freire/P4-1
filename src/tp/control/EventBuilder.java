package tp.control;

import tp.events.*;
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
	public Event Builder() {
		String dataId = null;
	         try {
				dataId=b.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	         Event finalEvent = null;
	         for (Event e : parser.parser) {
	        	 finalEvent=e.parser(dataId);
		         if (finalEvent!=null) {
		        	 break;
		         }
	         }
	         if (finalEvent!=null) {
	        	 finalEvent.builder(b);
	         } else {
	        	 //Throw invalid format file exception
	         }
	    	return finalEvent;
	}
	public EventsList finalBuilder() {
		EventsList t= new EventsList();
		t.EventsList.add(Builder());
		t.EventsList.add(Builder());
		t.EventsList.add(Builder());
		t.EventsList.add(Builder());
		t.EventsList.add(Builder());
		return t;
	}

}
