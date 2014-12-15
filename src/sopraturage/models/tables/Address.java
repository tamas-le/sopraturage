package sopraturage.models.tables;



public class Address {
	
	public enum WayType{
		rue,avenue,chemin,allée,boulevard,route,ruelle
	};
	
	private WayType waytype;
	private String wayName;
	private PostCode postCode;
	private int num;
	
	public Address(WayType waytype, String wayName, PostCode postCode, int num) {
		super();
		this.waytype = waytype;
		this.wayName = wayName;
		this.postCode = postCode;
		this.num = num;
	}

	public WayType getWaytype() {
		return waytype;
	}

	public void setWaytype(WayType waytype) {
		this.waytype = waytype;
	}

	public String getWayName() {
		return wayName;
	}

	public void setWayName(String wayName) {
		this.wayName = wayName;
	}

	public PostCode getPostCode() {
		return postCode;
	}

	public void setPostCode(PostCode postCode) {
		this.postCode = postCode;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
	
	

}
