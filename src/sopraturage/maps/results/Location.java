package sopraturage.maps.results;

public class Location {

	public float lat;
	public float lng;
	
	
	public Location(float lat, float lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
	
}
