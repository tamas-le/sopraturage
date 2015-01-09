package sopraturage.maps.results;

public class Geometry {
	
	public LatLng location;
	public String location_type;
	public Viewport viewport;
	
	public Geometry(LatLng location, String location_type, Viewport viewport) {
		super();
		this.location = location;
		this.location_type = location_type;
		this.viewport = viewport;
	}

	@Override
	public String toString() {
		return "Geometry [location=" + location + ", location_type="
				+ location_type + ", viewport=" + viewport + "]";
	}
	
	
	
	
	

}
