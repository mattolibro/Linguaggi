package management.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import management.view.windows.PeopleFoundWindow;

import management.model.Person;

public class SearchDispatcher {
	
	private final static String[] STUDY_KEYWORDS = new String[] {"study", "studied", "studies", "university", "school", "universities", "schools"};
	private final static Set<String> SET_STUDY_KEYWORDS = new HashSet<String>(Arrays.asList(STUDY_KEYWORDS)); 
	
	private static boolean searchForFieldStudy = false;
	
	public static void search(String request) {
		
		System.out.println("Request: "+ request +" --> ora cerco la risposta confrontando la mappa con le possibili domande. dammi un attimo");
		
		StringTokenizer st = new StringTokenizer(request);
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
			if(SET_STUDY_KEYWORDS.contains(token)) {
				System.out.println("ricerca del campo di studi");
				searchForFieldStudy = true;
			}
			
		}
		
		st = new StringTokenizer(request);
		TreeSet<Person> peopleFound = new TreeSet<Person>();
		if(searchForFieldStudy) {
			peopleFound = SearchForField.response("study", st);
		}
		
		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(request, peopleFound);
		peopleFoundWindow.setVisible(true);
	}
}
