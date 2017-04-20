package management.search;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

import management.core.Data;
import management.model.Person;

public class SearchForField {
	
	
	public static TreeSet<Person> response(String typeField, StringTokenizer values) {
		System.out.println("Searching studies");
		TreeSet<Person> peopleFound = new TreeSet<Person>();
		HashMap<String, TreeSet<String>> studies = Data.getMapSearches().get(typeField);
		for (String field : studies.keySet()) {
			StringTokenizer st = values;
			while(st.hasMoreTokens()) {
				String token = st.nextToken();
				System.out.println(field+" con "+token);
				if(field.toUpperCase().contains(token.toUpperCase())) {
					System.out.println("trovato una corrispondenza studio");
					peopleFound.add(Data.getPeople().get(studies.get(field)));
				}
			}
		}
		
		return peopleFound;
	}
	
}
