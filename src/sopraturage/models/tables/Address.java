package sopraturage.models.tables;



public class Address {
	

	private String waytype;
	private String wayName;
	private PostCode postCode;
	private int num;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address(String waytype, String wayName, PostCode postCode, int num) {
		super();
		this.waytype = waytype;
		this.wayName = wayName;
		this.postCode = postCode;
		this.num = num;
	}

	public String getWaytype() {
		return waytype;
	}

	public void setWaytype(String waytype) {
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


	
	
	@Override
	public String toString() {
		return "Address [waytype=" + waytype + ", wayName=" + wayName
				+ ", postCode=" + postCode + ", num=" + num + ", id=" + id
				+ "]";
	}

	public String toStringBetter(){
		return num+" "+waytype+" "+wayName+" "+postCode.toStringBetter();
	}
	
	
	
	
	

}
