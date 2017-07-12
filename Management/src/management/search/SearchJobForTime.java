package management.search;

import java.util.StringTokenizer;

import management.model.Job;
import management.model.Person;

public class SearchJobForTime {

	private final static String[] TIME_KEYWORDS = new String[] {"more", "less"};

	
	public static boolean response(String values, String field, Person person) {
		boolean response = false;
		
		StringTokenizer stringTokenizerValues = new StringTokenizer(values);
		int number = -1;
		boolean greater = false, lesser = false;
		while(stringTokenizerValues.hasMoreTokens()) {
			String tokenValue = stringTokenizerValues.nextToken();
			if(TIME_KEYWORDS[0].equalsIgnoreCase(tokenValue)) {
				System.out.println("greater");
				greater = true;
			}
			if(TIME_KEYWORDS[1].equalsIgnoreCase(tokenValue)) {
				System.out.println("lesser");
				lesser = true;
			}
			if(isParsable(tokenValue))
				number = Integer.parseInt(tokenValue);
		}

		if(number != -1) {
			for(Job job : person.getCv().getJobs()) {
				if(job.getNameJob().equals(field))
					System.out.println("bu");
			}
		}
		return response;
	}

	private static boolean isParsable(String input){
		boolean parsable = true;
		try{
			Integer.parseInt(input);
		}catch(NumberFormatException e){
			parsable = false;
		}
		return parsable;
	}

}
