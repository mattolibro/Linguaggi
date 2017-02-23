package management.test;

import management.model.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import management.model.Address;
import management.model.CV;
import management.model.PeopleJsonFactory;
import management.model.Person;

import javax.json.*;

public class TestJSON {

	@Test
	public void testJSONFormat() {
		File file = new File("files/grammarSample.json");
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			JsonReader rdr = Json.createReader(fis);
			JsonObject obj =  rdr.readObject();
			List<Person> person = new ArrayList<Person>();
			JsonArray results = obj.getJsonArray("People");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				//System.out.println(result.getJsonObject("Person").getString("ID"));
				PeopleJsonFactory people = new PeopleJsonFactory(result.getJsonObject("Person"));
				person.add(people.createPerson());
				//System.out.println(person.toString());
			}
			
			List<Study> studies = new ArrayList<Study>();
			studies.add(new Study("Computer Science", "Universita Bologna", LocalDate.parse("2011-09-11"), LocalDate.parse("2014-06-18")));
			studies.add(new Study("Master Software Architecture", "Universita Bologna", LocalDate.parse("2014-09-11"), LocalDate.parse("2017-06-18")));
			List<String> languagesKnown = new ArrayList<String>();
			languagesKnown.add("Italian");
			languagesKnown.add("English");
			List<Job> jobs = new ArrayList<Job>();
			jobs.add(new Job("Programmer", "Ubisoft", LocalDate.parse("2017-07-20"), null));
			Person personExpected = new Person("ID-000001", "Pippo", "Baudo", LocalDate.parse("1989-07-31"), new Address("via della vittoria, 21", "Bologna", "40100", "Monopoli"), new CV(studies, languagesKnown, jobs));
			//System.out.println(personExpected.toString());
			System.out.println(personExpected.toStringJSON());
			
			assertEquals(personExpected, person.get(0));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
