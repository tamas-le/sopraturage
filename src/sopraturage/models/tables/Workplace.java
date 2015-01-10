package sopraturage.models.tables;

public class Workplace extends Address {
	
	private String name;

	public Workplace(String waytype, String wayName, PostCode postCode,
			int num, String name) {
		super(waytype, wayName, postCode, num);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toStringBetter() {
		
		return name+" - "+ super.toStringBetter();
	}
	
	
	
	
	
	

}
