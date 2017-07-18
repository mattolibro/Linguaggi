package management.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import management.model.Address;
import management.model.CV;
import management.model.Job;
import management.model.Person;
import management.model.Study;

public class TestCalculateTimeDuty {
	
	@Test
	public void testCalculateTimeDuty() {
		List<Study> studies = new ArrayList<Study>();
		studies.add(new Study("Computer Science", "Universita Bologna", LocalDate.parse("2011-09-11"), LocalDate.parse("2014-06-18")));
		studies.add(new Study("Master Software Architecture", "Universita Bologna", LocalDate.parse("2014-09-11"), LocalDate.parse("2017-06-18")));
		List<String> languagesKnown = new ArrayList<String>();
		languagesKnown.add("Italian");
		languagesKnown.add("English");
		List<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("Programmer", "Ubisoft", LocalDate.parse("2014-07-10"), null));
		Person personExpected = new Person("ID-000001", "Pippo", "Baudo", LocalDate.parse("1989-07-31"), new Address("via della vittoria, 21", "Bologna", "40100", "Monopoli"), new CV(studies, languagesKnown, jobs));
		System.out.println(personExpected.toString());
		System.out.println(personExpected.getCv().getJobs().get(0).calculateYearsOfDuty());
		
	}
}
