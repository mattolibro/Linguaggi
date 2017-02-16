package management.model;

import java.time.LocalDate;

import javax.json.JsonObject;

public class Study extends Duty {

	private String nameStudy;

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
}
