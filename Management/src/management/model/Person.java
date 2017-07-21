package management.model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Person {
	
	private String ID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Address address;
	private CV cv;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
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
	
	public void setDateOfBirth(String dateOfBirth) {
		if(!dateOfBirth.equals("")) // useful for debugging
			this.dateOfBirth = LocalDate.parse(dateOfBirth);
		else
			this.dateOfBirth = null;
		
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
		s += "ID: "+ID+"\n";
		s += "First Name: "+firstName+"\n";
		s += "Last Name: "+lastName+"\n";
		s += "Date of birth: "+dateOfBirth.getDayOfMonth()+"/"+dateOfBirth.getMonthValue()+"/"+dateOfBirth.getYear()+"\n";
		s += address.toString();
		s += cv.toString();
		s +="\n";
		return s;
	}
	
	public String toStringJSON() {
		String s = "    {\n      \"Person\": {\n";
		s += "        \"ID\": \""+ID+"\",\n";
		s += "        \"FirstName\": \""+firstName+"\",\n";
		s += "        \"LastName\": \""+lastName+"\",\n";
		s += "        \"DateOfBirth\": \""+dateOfBirth.toString()+"\",\n";
		s += address.toStringJSON();
		s += cv.toStringJSON();
		s += "    }\n";
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
		Person p = (Person) obj;
		
		if(ID.equals(p.ID) && firstName.equals(p.firstName) && lastName.equals(p.lastName) && dateOfBirth.isEqual(p.dateOfBirth) && 
				address.equals(p.address))
			result = true;
		return result;
	}
	
	public Job getJob(String jobName) {
		Job job = null;
		for(Job j: this.getCv().getJobs()) {
			if(j.getNameJob().equalsIgnoreCase(jobName)){
				job = j;
				break;
			}
		}
		return job;
	}
	
	public int getAge() {
		return (int) ChronoUnit.YEARS.between(this.dateOfBirth, LocalDate.now());
	}
}
