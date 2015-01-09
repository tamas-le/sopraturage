package sopraturage.models.tables;



public class Address {
	

	protected String waytype;
	protected String wayName;
	protected PostCode postCode;
	protected int num;
	protected int id;
	protected double lon;
	protected double lat;
	
	
	
	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

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

	@Override
	public boolean equals(Object obj) {
		Address another =(Address)obj;
		if (postCode.equals(another.postCode)){
			if (this.waytype.equals(another.waytype)){
				if (this.num==another.num){
					return (wayName.equals(another.wayName));
				}
			}
			
		}
		return false;
	}
	
	
	
	
	
	
	
	

}
