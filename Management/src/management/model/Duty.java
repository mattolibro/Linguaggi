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

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
