package sopraturage.maps.results;

public class Viewport {
	public Location northeast;
	public Location southwest;
	public Viewport(Location northeast, Location southwest) {
		super();
		this.northeast = northeast;
		this.southwest = southwest;
	}
	
	@Override
	public String toString() {
		return "Viewport [northeast=" + northeast + ", southwest=" + southwest
				+ "]";
	}

	
	
	
	

}
