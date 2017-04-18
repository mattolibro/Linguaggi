package management.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CV {
	private List<Study> studies;
	private List<String> languagesKnown;
	private List<Job> jobs;

	public CV(List<Study> studies, List<String> languagesKnown, List<Job> jobs) {
		this.studies = studies;
		this.languagesKnown = languagesKnown;
		this.jobs = jobs;
	}

	public CV() {
		this(new ArrayList<Study>(), new ArrayList<String>(), new ArrayList<Job>());
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

	public void AddStudy(Study s) {
		studies.add(s);
	}

	public void AddLanguage(String l) {
		languagesKnown.add(l);
	}

	public void AddJob(Job j) {
		jobs.add(j);
	}

	@Override
	public String toString() {
		String s = "";
		for (Study study : studies) {
			s += study.toString();
		}
		s += "Languages Known: ";
		for (int i = 0; i < languagesKnown.size(); i++) {
			if(i == languagesKnown.size()-1)
				s += languagesKnown.get(i);
			else
				s += languagesKnown.get(i)+", ";
		}
		s +="\n";
		for (Job job : jobs) {
			s += job.toString();
		}

		return s;
	}

	public String toStringJSON() {
		String s = "        \"CV\": {\n";
		s += "          \"Studies\": [\n";	
		for (int i = 0; i < studies.size(); i++) {
			if(i == studies.size()-1)
				s += studies.get(i).toStringJSON()+"\n";
			else
				s += studies.get(i).toStringJSON()+",\n";
		}
		s +="          ],\n";
		s += "          \"LanguagesKnown\": [\n";
		for (int i = 0; i < languagesKnown.size(); i++) {
			if (i == languagesKnown.size()-1)
				s += "            \""+languagesKnown.get(i)+"\"\n";
			else
				s += "            \""+languagesKnown.get(i)+"\",\n";
		}

		s +="          ],\n";
		s += "          \"Jobs\": [\n";	
		for (int i = 0; i < jobs.size(); i++) {
			if(i == jobs.size()-1)
				s += jobs.get(i).toStringJSON()+"\n";
			else
				s += jobs.get(i).toStringJSON()+",\n";
		}
		s += "          ]\n        }\n      }\n";
		return s;
	}

	private static boolean listStudyEqualsNoOrder(List<Study> l1, List<Study> l2) {
		final Set<Study> s1 = new HashSet<>(l1);
		final Set<Study> s2 = new HashSet<>(l2);

		return s1.equals(s2);
	}

	private static boolean listLanguageEqualsNoOrder(List<String> l1, List<String> l2) {
		final Set<String> s1 = new HashSet<>(l1);
		final Set<String> s2 = new HashSet<>(l2);

		return s1.equals(s2);
	}

	private static boolean listJobEqualsNoOrder(List<Job> l1, List<Job> l2) {
		final Set<Job> s1 = new HashSet<>(l1);
		final Set<Job> s2 = new HashSet<>(l2);

		return s1.equals(s2);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		CV cv = (CV) obj;
		if(listStudyEqualsNoOrder(studies, cv.studies) && listLanguageEqualsNoOrder(languagesKnown, cv.languagesKnown) && 
				listJobEqualsNoOrder(jobs, cv.jobs))
			result = true;

		return result;
	}

}
