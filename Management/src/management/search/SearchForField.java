package management.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import management.data.Data;
import management.model.Job;
import management.model.Person;

public class SearchForField {


	public static HashSet<Person> response(String typeField, String field, int time, boolean recently) { //typeField = (job | study)
		HashSet<Person> peopleFound = new HashSet<Person>();
		HashMap<String, TreeSet<String>> map = Data.getMapSearches().get(typeField); // it gets the map for the right field (es. study)
		TreeSet<String> peopleID = map.get(field);
		System.out.println("Ricerca per "+typeField+" e tempo="+time+" recently: "+recently);

		if(peopleID != null)
			if(!recently) {
				if(time == 0){
					for (String IDperson : peopleID) {
						System.out.println("Person found: " +IDperson);
						peopleFound.add(Data.getPeople().get(IDperson));
					}
				}else {
					for (String IDperson : peopleID) {
						Person person = Data.getPeople().get(IDperson);
						Job job = person.getJob(field);
						if(job.calculateYearsOfDuty() >= time){
							System.out.println("Person found: " +IDperson);
							peopleFound.add(person);
						}
					}
				}
			}
			else {
				if(time == 0) //set few years as 2 years
					time = 2; 
				for (String IDperson : peopleID) {
					Person person = Data.getPeople().get(IDperson);
					Job job = person.getJob(field);
					if(job.calculateHowLongYearsOfDuty() <= time){
						System.out.println("Person found: " +IDperson);
						peopleFound.add(person);
					}
				}
				
			}


		return peopleFound;
	}

}
