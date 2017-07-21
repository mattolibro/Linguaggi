package management.search;

import java.util.HashSet;

import management.data.Data;
import management.model.Person;

public class SearchForAge {


	/**
	 * 3 different modes for searching people for age
	 * 
	 * 1)"" - just the age
	 * 2)"younger|older|less|more" - younger/older than the age
	 * 3)"between" - between two ages
	 */
	public static HashSet<Person> response(int age) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		for (String IDPerson : Data.getPeople().keySet()) {
			if(Data.getPeople().get(IDPerson).getAge() == age){
				peopleFound.add(Data.getPeople().get(IDPerson));
			}
		}

		return peopleFound;
	}

	public static HashSet<Person> response(String mode, int age) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		for (String IDPerson : Data.getPeople().keySet()) {
			if(mode.equalsIgnoreCase("younger") || mode.equalsIgnoreCase("less")){
				if(Data.getPeople().get(IDPerson).getAge() <= age){
					peopleFound.add(Data.getPeople().get(IDPerson));
				}
			}else if(mode.equalsIgnoreCase("older") || mode.equalsIgnoreCase("more")){
				if(Data.getPeople().get(IDPerson).getAge() >= age){
					peopleFound.add(Data.getPeople().get(IDPerson));
				}
			}
		}

		return peopleFound;
	}

	public static HashSet<Person> response(int age1, int age2) {
		HashSet<Person> peopleFound = new HashSet<Person>();
		for (String IDPerson : Data.getPeople().keySet()) {
			if(Data.getPeople().get(IDPerson).getAge() >= age1 && Data.getPeople().get(IDPerson).getAge() <= age2){
				peopleFound.add(Data.getPeople().get(IDPerson));
			}
		}

		return peopleFound;
	}

}
