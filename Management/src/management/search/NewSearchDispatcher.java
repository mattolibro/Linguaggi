package management.search;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import management.model.Person;
import management.view.windows.PeopleFoundWindow;


public class NewSearchDispatcher {

	private final static String STUDY_STRPATTERN = "(study|studies|studied)\\s+(.+)"; //(study|studies|studied)\\s+(\\S+)(?:\\s+(\\S+))?
	private final static String JOB_STRPATTERN = "(work|works|worked)\\s+(as)\\s+(.+)";
	private final static String JOBTIME_STRPATTERN = "(work|works|worked)\\s+(as)\\s+(.+)\\s(for|for at least)\\s(\\d+)\\s(years|year)?";
	//private final static String AGE_STRPATTERN = "";
	//private final static String LANGUAGE_STRPATTERN = "";
	private final static Pattern STUDY_PATTERN = Pattern.compile(STUDY_STRPATTERN);
	private final static Pattern JOB_PATTERN = Pattern.compile(JOB_STRPATTERN);
	private final static Pattern JOBTIME_PATTERN = Pattern.compile(JOBTIME_STRPATTERN);

	public static void search(String request) {

		System.out.println("Request: "+ request +" --> ora cerco la risposta confrontando la mappa con le possibili domande. dammi un attimo");
		Matcher matcherStudy = STUDY_PATTERN.matcher(request);
		Matcher matcherJob = JOB_PATTERN.matcher(request);
		Matcher matcherJobTime = JOBTIME_PATTERN.matcher(request);

		HashSet<Person> peopleFound = new HashSet<Person>();

		if (matcherStudy.find()) {
			System.out.println("Search about study field");
			matcherStudy = STUDY_PATTERN.matcher(request);
			while(matcherStudy.find()) {
				String field = matcherStudy.group(2);
				System.out.println("Searching for studies, field's value: "+field);
				peopleFound = SearchForField.response("study", field, 0);
			}
		}
		else if(matcherJobTime.find()) {
			System.out.println("Search about job field and time");
			matcherJobTime = JOBTIME_PATTERN.matcher(request);
			while(matcherJobTime.find()) {
				String field = matcherJobTime.group(3);
				int time = Integer.parseInt(matcherJobTime.group(5));
				System.out.println("Searching for jobs, field's value: "+field);
				peopleFound = SearchForField.response("job", field, time);
			}
			
		}
		else if (matcherJob.find()) {
			System.out.println("Search about job field");
			matcherJob = JOB_PATTERN.matcher(request);
			while(matcherJob.find()) {
				String field = matcherJob.group(3);
				System.out.println("Searching for jobs, field's value: "+field);
				peopleFound = SearchForField.response("job", field, 0);
			}
		}


		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(request, peopleFound);
		peopleFoundWindow.setVisible(true);
	}
}
