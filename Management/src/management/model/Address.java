package management.model;

public class Address {
	private String street;
	private String city;
	private String postalCode;
	private String country;
	
    public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String street, String city, String postalCode, String country) {
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "Street: "+street+"\n";
		s += "City: "+city+"\n";
		s += "Postal Code: "+postalCode+"\n";
		s += "Country: "+country+"\n";
		return s;
	}
	
	public String toStringJSON() {
		String s = "        \"Address\": {\n";
		s += "          \"Street\": \""+street+"\",\n";
		s += "          \"City\": \""+city+"\",\n";
		s += "          \"Postal Code\": \""+postalCode+"\",\n";
		s += "          \"Country\": \""+country+"\"\n";
		s += "        },\n";
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
		Address a = (Address) obj;
		
		if(street.equals(a.street) && city.equals(a.city) && postalCode.equals(a.postalCode) && country.equals(a.country))
			result = true;
		return result;
	}	
}
