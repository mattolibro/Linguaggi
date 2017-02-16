package management.model;

import java.util.List;

public class CV {
	private List<Study> studies;
	private List<String> languagesKnown;
	private List<Job> jobs;
		
	public CV(List<Study> studies, List<String> languagesKnown, List<Job> jobs) {
		this.studies = studies;
		this.languagesKnown = languagesKnown;
		this.jobs = jobs;
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
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
