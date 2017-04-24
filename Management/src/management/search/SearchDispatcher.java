package management.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import management.view.windows.PeopleFoundWindow;

import management.model.Person;

public class SearchDispatcher {
	
	private final static String[] STUDY_KEYWORDS = new String[] {"study", "studied", "studies", "studing", "university", "school", "universities", "schools"};
	private final static String[] JOB_KEYWORDS = new String[] {"work", "worked", "works", "job", "working"};
	private final static String[] TIME_KEYWORDS = new String[] {"more", "less", "than"};
	private final static Set<String> SET_STUDY_KEYWORDS = new HashSet<String>(Arrays.asList(STUDY_KEYWORDS));
	private final static Set<String> SET_JOB_KEYWORDS = new HashSet<String>(Arrays.asList(JOB_KEYWORDS));
	private final static Set<String> SET_TIME_KEYWORDS = new HashSet<String>(Arrays.asList(TIME_KEYWORDS));
	
	private static boolean searchForFieldStudy = false;
	private static boolean searchForFieldJob = false;
	private static boolean searchForTime = false;
	
	public static void search(String request) {
		
		System.out.println("Request: "+ request +" --> ora cerco la risposta confrontando la mappa con le possibili domande. dammi un attimo");
		
		StringTokenizer st = new StringTokenizer(request);
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
			if(SET_STUDY_KEYWORDS.contains(token)) {
				searchForFieldStudy = true;
			}
			if(SET_JOB_KEYWORDS.contains(token)) {
				searchForFieldJob = true;
			}
			if(SET_TIME_KEYWORDS.contains(token)) {
				searchForTime = true;
			}
		}
		
		st = new StringTokenizer(request);
		HashSet<Person> peopleFound = new HashSet<Person>();
		if(searchForFieldStudy) {
			peopleFound = SearchForField.response("study", request, searchForTime);
			searchForFieldStudy = false; // reset after the search
		}
		else if (searchForFieldJob) {
			peopleFound = SearchForField.response("job", request, searchForTime);
			searchForFieldJob = false; // reset after the search
		}
		searchForTime = false;
		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(request, peopleFound);
		peopleFoundWindow.setVisible(true);
	}
}
