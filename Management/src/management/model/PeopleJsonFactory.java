package management.model;

import java.time.LocalDate;

import javax.json.JsonObject;

public class PeopleJsonFactory {
	private JsonObject jsonObject;
	
	public PeopleJsonFactory(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	public Person createPerson() {
		return new Person(jsonObject.getString("ID"), jsonObject.getString("FirstName"), jsonObject.getString("LastName"),
				LocalDate.parse(jsonObject.getString("DateOfBirth")), new Address(jsonObject.getJsonObject("Address")), new CV(jsonObject.getJsonObject("CV")));
	}
}
