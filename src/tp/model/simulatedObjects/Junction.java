package tp.model.simulatedObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Junction {
	Map<List<Vehicle>,Road> queues;
	Iterator<Map.Entry<List<Vehicle>,Road>> it;
	String id;
	
	public Junction(String id, List<Road> incoming) {
		for(Road r : incoming)
			queues.put(new ArrayList<Vehicle>(), r);
		it = queues.entrySet().iterator();
	}
	public void enterVehicle(Vehicle vehicle, Road r) {
		for (Map.Entry<List<Vehicle>,Road> entry : queues.entrySet())
			if(entry.getValue() == r)
				entry.getKey().add(vehicle);
	}
	public void advance() {
		if(!it.hasNext())
			it = queues.entrySet().iterator();
		try {
		Map.Entry<List<Vehicle>,Road> pair = it.next();
		if(!pair.getKey().isEmpty()) {
			pair.getKey().get(0).advanceToNextRoad();
			pair.getKey().remove(0);
		}
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.out.println("Disconnected junction. This should never happen.");
		}
	}
	public void generateReport(int time) {
		/*
		 * [junction_report] 
		 * id = j2 
		 * time = 5 
		 * queues = (r1,red,[]),(r2,green,[v3,v2,v5]),(r3,red,[v1,v4]) 
		 */
		String queuesString = new String();
		Iterator<Map.Entry<List<Vehicle>,Road>> it = queues.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<List<Vehicle>,Road> entry = it.next();
			queuesString += "(";
			for(int t = 0; t < entry.getKey().size(); t++) {
				queuesString += entry.getKey().get(t).getID();
				if(t < queues.size()-1)
					queuesString += ",";
			}
			if(it.hasNext())
				queuesString += ")";
			it.next();
		}
		System.out.println("[junction_report]\nid = " + id + "\n time = " + time + "\n queues = " + queuesString);
	}

}
