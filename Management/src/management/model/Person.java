package management.model;
import java.time.LocalDate;

import javax.json.*;

public class Person {
	
	private String ID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	
	public Person(JsonObject jsonObject) {
		this.ID = jsonObject.getString("ID");
		this.firstName = jsonObject.getString("FirstName");
		this.lastName = jsonObject.getString("LastName");
		this.dateOfBirth = LocalDate.parse(jsonObject.getString("DateOfBirth"));
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
	
	
	
}
