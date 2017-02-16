package management.model;

import javax.json.JsonObject;

public class Address {
	private String street;
	private String city;
	private String postalCode;
	private String country;
	
	public Address(JsonObject jsonObject) {
		this.street = jsonObject.getString("Street");
		this.city = jsonObject.getString("City");
		this.postalCode = jsonObject.getString("PostalCode");
		this.country = jsonObject.getString("Country");
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
	
}
