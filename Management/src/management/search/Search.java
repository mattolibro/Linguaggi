package management.search;

import java.util.List;

import management.model.Person;
import management.view.windows.PeopleFoundWindow;

public class Search {

	private List<Person> people;
	
	public Search(List<Person> people) {
		this.people = people;
	}


	public void search(String query) {
		System.out.println(query+"--> ora cerco la risposta confrontando la mappa con le possibili domande. dammi un attimo");
		
		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(query);
		peopleFoundWindow.setVisible(true);
	}
}
