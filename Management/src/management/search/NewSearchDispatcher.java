package management.search;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import management.model.Person;
import management.view.windows.PeopleFoundWindow;


public class NewSearchDispatcher {

	private final static String STUDY_STRPATTERN = "(study|studies|studied)\\s+(.+)"; //(study|studies|studied)\\s+(\\S+)(?:\\s+(\\S+))?
	private final static String JOB_STRPATTERN = "(work|works|worked)\\s+(?:as)\\s+(.+)";
	private final static String JOBTIME_STRPATTERN = "(work|works|worked)\\s+(?:as)\\s+(.+)\\s+(for|for at least)\\s+(\\d+)(\\s+years|year)?";
	private final static String AGE_STRPATTERN = "(is|are)\\s+((\\d+)|(less|younger|more|older)\\s+than\\s+(\\d+)|(between)\\s+(\\d+)\\s+and\\s+(\\d+))";
	private final static String LANGUAGE_STRPATTERN = "(?:knows|know|understands|understand|speaks|speak)\\s+(?:more than|at least|at least more than)\\s+(\\d+)\\s+(?:languages|language)(?:\\s+and (1|one) is (english|English))?";
	
	private final static Pattern STUDY_PATTERN = Pattern.compile(STUDY_STRPATTERN);
	private final static Pattern JOB_PATTERN = Pattern.compile(JOB_STRPATTERN);
	private final static Pattern JOBTIME_PATTERN = Pattern.compile(JOBTIME_STRPATTERN);
	private final static Pattern AGE_PATTERN = Pattern.compile(AGE_STRPATTERN);
	private final static Pattern LANGUAGE_PATTERN = Pattern.compile(LANGUAGE_STRPATTERN);

	public static void search(String request) {

		System.out.println("Request: "+ request +" --> searching the answer checking the different question. it will take some time");
		Matcher matcherStudy = STUDY_PATTERN.matcher(request);
		Matcher matcherJob = JOB_PATTERN.matcher(request);
		Matcher matcherJobTime = JOBTIME_PATTERN.matcher(request);
		Matcher matcherAge = AGE_PATTERN.matcher(request);
		Matcher matcherLanguage = LANGUAGE_PATTERN.matcher(request);

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
				String field = matcherJobTime.group(2);
				int time = Integer.parseInt(matcherJobTime.group(4));
				System.out.println("Searching for jobs, field's value: "+field);
				peopleFound = SearchForField.response("job", field, time);
			}
			
		}
		else if (matcherJob.find()) {
			System.out.println("Search about job field");
			matcherJob = JOB_PATTERN.matcher(request);
			while(matcherJob.find()) {
				String field = matcherJob.group(2);
				System.out.println("Searching for jobs, field's value: "+field);
				peopleFound = SearchForField.response("job", field, 0);
			}
		}
		else if(matcherAge.find()) {
			System.out.println("Search about age");
			matcherAge = AGE_PATTERN.matcher(request);
			while(matcherAge.find()) {
				if (matcherAge.group(3) != null) {
					int age = Integer.parseInt(matcherAge.group(3));
					peopleFound = SearchForAge.response(age);
					
				}else if(matcherAge.group(4) != null) {
					String mode = matcherAge.group(4);
					int age = Integer.parseInt(matcherAge.group(5));
					peopleFound = SearchForAge.response(mode, age);
				
				}else if(matcherAge.group(6) != null) {
					int age1 = Integer.parseInt(matcherAge.group(7));
					int age2 = Integer.parseInt(matcherAge.group(8));
					peopleFound = SearchForAge.response(age1, age2);
					
				}
			}
		}
		else if(matcherLanguage.find()) {
			System.out.println("Search about language");
			matcherLanguage = LANGUAGE_PATTERN.matcher(request);
			while(matcherLanguage.find()) {
				int numLanguages = Integer.parseInt(matcherLanguage.group(1));
				boolean english = false;
				if(matcherLanguage.group(2) != null)
					english = true;
				System.out.println("Searching for more than "+numLanguages+" and english: "+english);
				peopleFound = SearchForLanguage.response(numLanguages, english);
			}
		}


		/*-------Answer in a frame-----------------*/
		PeopleFoundWindow peopleFoundWindow = new PeopleFoundWindow(request, peopleFound);
		peopleFoundWindow.setVisible(true);
	}
}
