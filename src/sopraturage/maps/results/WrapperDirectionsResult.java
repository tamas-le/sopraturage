package sopraturage.maps.results;

import java.util.Arrays;

public class WrapperDirectionsResult extends WrapperResult {
	
	public DirectionsRoute[] routes;
	
	
	
	@Override
	public String toString() {
		return "WrapperResult [results=" + Arrays.toString(routes)
				+ ", status=" + status + "]";
	}


	public WrapperDirectionsResult(DirectionsRoute[] routes, String status) {
		super(status);
		this.routes = routes;
	}
	
	

}
