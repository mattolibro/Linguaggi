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

	public Job() {
		// TODO Auto-generated constructor stub
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
	
	public String getNameJob() {
		return nameJob;
	}
	
	public void setNameJob(String nameJob) {
		this.nameJob = nameJob;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Name Job: "+nameJob+"\n";
		s += super.toString();
		return s;
	}
	
	public String toStringJSON() {
		String s = "            {\n              \"Job\": {\n";
		s += "                \"NameJob\": \""+nameJob+"\",\n";
		s += super.toStringJSON();
		return s;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		Job s = (Job) obj;
		
		if(nameJob.equals(s.nameJob) && super.equals(obj))
			result = true;
		
		return result;
	}
}