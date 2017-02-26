package management.model;

import java.time.LocalDate;

import javax.json.JsonObject;

public class Study extends Duty {

	private String nameStudy;

	public Study(String nameStudy, String at, LocalDate startDate, LocalDate endDate) {
		this.nameStudy = nameStudy;
		super.at = at;
		super.startDate = startDate;
		super.endDate = endDate;
	}
	
	public Study() {
		// TODO Auto-generated constructor stub
	}

	public Study(JsonObject jsonObject) {
		this.nameStudy = jsonObject.getString("NameStudy");
		super.at = jsonObject.getString("At");
		super.startDate = LocalDate.parse(jsonObject.getString("StartDate"));
		if(!jsonObject.getString("EndDate").isEmpty())
			super.endDate = LocalDate.parse(jsonObject.getString("EndDate"));
		else
			super.endDate = null;
	}	

	public String getNameStudy() {
		return nameStudy;
	}

	public void setNameStudy(String nameStudy) {
		this.nameStudy = nameStudy;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Name Study: "+nameStudy+"\n";
		s += super.toString();
		return s;
	}
	
	public String toStringJSON() {
		String s = "            {\n            \"Study\": {\n";
		s += "                \"NameStudy\": \""+nameStudy+"\",\n";
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
		Study s = (Study) obj;
		
		if(nameStudy.equals(s.nameStudy) && super.equals(obj))
			result = true;
		
		return result;
	}
}
