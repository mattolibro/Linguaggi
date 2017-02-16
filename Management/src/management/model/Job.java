package management.model;

import java.time.LocalDate;

import javax.json.JsonObject;

public class Job extends Duty {

	private String nameJob;
	
	public Job(String nameJob, String at, LocalDate startDate, LocalDate endDate) {
		this.nameJob = nameJob;
		super.at = at;
		super.startDate = startDate;
		super.endDate = endDate;
	}	
	
	public Job(JsonObject jsonObject) {
		this.nameJob = jsonObject.getString("NameJob");
		super.at = jsonObject.getString("At");
		super.startDate = LocalDate.parse(jsonObject.getString("StartDate"));
		if(!jsonObject.getString("EndDate").isEmpty())
			super.endDate = LocalDate.parse(jsonObject.getString("EndDate"));
		else
			super.endDate = null;
	}
	
	public String getJobStudy() {
		return nameJob;
	}
	
	public void setJobStudy(String nameJob) {
		this.nameJob = nameJob;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Job Study: "+nameJob+"\n";
		s += super.toString();
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