package management.search;

import java.util.HashSet;

import management.data.Data;
import management.model.Person;

public class SearchForLanguage {
	public static HashSet<Person> response(int numLanguages, boolean english) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		for (String IDPerson : Data.getPeople().keySet()) {
			if(Data.getPeople().get(IDPerson).getCv().getNumLanguages() > numLanguages){
				if(!english) {
					peopleFound.add(Data.getPeople().get(IDPerson));
				}
				else {
					if(Data.getPeople().get(IDPerson).getCv().knowsEnglish())
						peopleFound.add(Data.getPeople().get(IDPerson));
				}
				
				
			}
		}

		return peopleFound;
	}
}
