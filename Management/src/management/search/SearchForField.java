package management.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import management.data.Data;
import management.model.Person;

public class SearchForField {


	public static HashSet<Person> response(String typeField, String field, boolean time) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		HashMap<String, TreeSet<String>> map = Data.getMapSearches().get(typeField); // it gets the map for the right field (es. study)
		TreeSet<String> peopleID = map.get(field);
		System.out.println("Ricerca per "+typeField+" e tempo="+time);
		
		if(peopleID != null)
			for (String IDperson : peopleID) {
				System.out.println("Person found: " +IDperson);
				peopleFound.add(Data.getPeople().get(IDperson));
			}

		return peopleFound;
	}

}
