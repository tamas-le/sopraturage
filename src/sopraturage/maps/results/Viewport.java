package sopraturage.maps.results;

public class Viewport {
	public LatLng northeast;
	public LatLng southwest;
	public Viewport(LatLng northeast, LatLng southwest) {
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
