package sopraturage.models.tables;

public class PostCode {
	
	@Override
	public boolean equals(Object another) {
		PostCode postcode=(PostCode)another;
		return this.postcode.equals(postcode.getPostcode()) && this.city.equals(postcode.getCity());
	}


	private String postcode;
	private String city;
	
	
	public PostCode(String postcode, String city) {
		super();
		this.postcode = postcode;
		this.city = city;
	}
	
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}


	@Override
	public String toString() {
		return "PostCode [postcode=" + postcode + ", city=" + city + "]";
	}


	public String toStringBetter() {
	
		return postcode+" "+city;
	}
	
	
	
	
	
	
	

}
