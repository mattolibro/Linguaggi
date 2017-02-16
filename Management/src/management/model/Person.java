package management.model;
import java.time.LocalDate;

import javax.json.*;

public class Person {
	
	private String ID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Address address;
	private CV cv;
	
	
	public Person(String iD, String firstName, String lastName, LocalDate dateOfBirth, Address address, CV cv) {
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.cv = cv;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "ID :"+ID+"\n";
		s += "First Name :"+firstName+"\n";
		s += "Last Name :"+lastName+"\n";
		s += "Date of birth :"+dateOfBirth.getDayOfMonth()+"/"+dateOfBirth.getMonthValue()+"/"+dateOfBirth.getYear()+"\n";
		s += address.toString();
		s += cv.toString();
		return s;
	}
}
