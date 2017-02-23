package management.model;

import java.time.LocalDate;


public abstract class Duty {
	
	protected String at;
	protected LocalDate startDate;
	protected LocalDate endDate;

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = LocalDate.parse(startDate);
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String endDate) {
		if(!endDate.equals(""))
			this.endDate = LocalDate.parse(endDate);
		else
			this.endDate = null;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "At: "+at+"\n";
		s += "Date of start: "+startDate.getDayOfMonth()+"/"+startDate.getMonthValue()+"/"+startDate.getYear()+"\n";
		if(endDate != null)
			s += "Date of end: "+endDate.getDayOfMonth()+"/"+endDate.getMonthValue()+"/"+endDate.getYear()+"\n";
		else
			s += "Date of end: -";
		
		return s;
	}
	
	public String toStringJSON() {
		String s = "";
		s += "\"At\": \""+at+"\",\n";
		s += "\"DateOfStart\": \""+startDate.toString()+"\",\n";
		if(endDate != null)
			s += "\"DateOfEnd\": \""+endDate.toString()+"\"\n";
		else
			s += "\"DateOfEnd\": \"\"\n";
		s += "}\n}";
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
		Duty d = (Duty) obj;
		
		if(at.equals(d) && startDate.isEqual(d.startDate) && endDate.isEqual(d.endDate))
			result = true;
		
		return result;
	}
}
