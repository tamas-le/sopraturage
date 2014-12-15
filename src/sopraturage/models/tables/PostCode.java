package sopraturage.models.tables;

public class PostCode {
	
	private int [] postcode;
	private String city;
	
	
	public PostCode(int[] postcode, String city) {
		super();
		this.postcode = postcode;
		this.city = city;
	}
	
	
	public int[] getPostcode() {
		return postcode;
	}
	public void setPostcode(int[] postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	

}
