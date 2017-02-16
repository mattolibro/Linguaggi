package management.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class CV {
	private List<Study> studies;
	private List<String> languagesKnown;
	private List<Job> jobs;
	
	public CV(JsonObject jsonObject) {
		studies = new ArrayList<Study>();
		JsonArray arrayStudies = jsonObject.getJsonArray("Studies");
		for(JsonObject studyJsonObject : arrayStudies.getValuesAs(JsonObject.class)) {
			Study study = new Study(studyJsonObject.getJsonObject("Study"));
			studies.add(study);
		}
	
		languagesKnown = new ArrayList<String>();
		JsonArray arrayLanguages = jsonObject.getJsonArray("LanguagesKnown");
		for (int i = 0; i < arrayLanguages.size(); i++) {
			languagesKnown.add(arrayLanguages.getString(i));
		}
		
		jobs = new ArrayList<Job>();
		JsonArray arrayJobs = jsonObject.getJsonArray("Jobs");
		for(JsonObject jobJsonObject : arrayJobs.getValuesAs(JsonObject.class)) {
			Job job = new Job(jobJsonObject.getJsonObject("Job"));
			jobs.add(job);
		}
		
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public List<String> getLanguagesKnown() {
		return languagesKnown;
	}

	public void setLanguagesKnown(List<String> languagesKnown) {
		this.languagesKnown = languagesKnown;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Study study : studies) {
			s += study.toString();
		}
		s += "Languages Known: ";
		for (String language : languagesKnown) {
			s += language+", ";
		}
		s +="\n";
		for (Job job : jobs) {
			s += job.toString();
		}
		
		return s;
	}

}
