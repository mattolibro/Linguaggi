package management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class PeopleJsonFactory {
	private JsonObject jsonObject;

	public PeopleJsonFactory(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public Person createPerson() {
		return new Person(jsonObject.getString("ID"), jsonObject.getString("FirstName"), jsonObject.getString("LastName"),
				LocalDate.parse(jsonObject.getString("DateOfBirth")), createAddress(), createCV());
	}

	public Address createAddress() {
		JsonObject addressJsonObject = jsonObject.getJsonObject("Address");
		return new Address(addressJsonObject.getString("Street"), addressJsonObject.getString("City"), 
				addressJsonObject.getString("PostalCode"), addressJsonObject.getString("Country"));
	}

	public CV createCV() {
		JsonObject cvJsonObject = jsonObject.getJsonObject("CV");
		List<Study> studies = new ArrayList<Study>();
		JsonArray arrayStudies = cvJsonObject.getJsonArray("Studies");
		for(JsonObject studyJsonObject : arrayStudies.getValuesAs(JsonObject.class)) {
			Study study = new Study(studyJsonObject.getJsonObject("Study"));
			studies.add(study);
		}

		List<String> languagesKnown = new ArrayList<String>();
		JsonArray arrayLanguages = cvJsonObject.getJsonArray("LanguagesKnown");
		for (int i = 0; i < arrayLanguages.size(); i++) {
			languagesKnown.add(arrayLanguages.getString(i));
		}

		List<Job> jobs = new ArrayList<Job>();
		JsonArray arrayJobs = cvJsonObject.getJsonArray("Jobs");
		for(JsonObject jobJsonObject : arrayJobs.getValuesAs(JsonObject.class)) {
			Job job = new Job(jobJsonObject.getJsonObject("Job"));
			jobs.add(job);
		}

		return new CV(studies, languagesKnown, jobs);
	}
}
