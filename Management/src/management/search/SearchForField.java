package management.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import management.core.Data;
import management.model.Person;

public class SearchForField {
	
	
	public static HashSet<Person> response(String typeField, String values, boolean time) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		HashMap<String, TreeSet<String>> map = Data.getMapSearches().get(typeField); // it gets the map for the right field (es. study)
		System.out.println("Ricerca per "+typeField);
		for (String field : map.keySet()) {
			StringTokenizer stringTokenizerValues = new StringTokenizer(values);
			while(stringTokenizerValues.hasMoreTokens()) {
				String tokenValue = stringTokenizerValues.nextToken();
				StringTokenizer stringTokenizerField = new StringTokenizer(field);
				while(stringTokenizerField.hasMoreTokens()) {
					if(stringTokenizerField.nextToken().equalsIgnoreCase(tokenValue)) {
						for(String id : map.get(field)) {
							peopleFound.add(Data.getPeople().get(id));
						}				
					}
				}
			}
		}
	
		return peopleFound;
	}
	
}
