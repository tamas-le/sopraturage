package sopraturage.maps.results;

import java.util.Arrays;

public class WrapperCoordinatesResult extends WrapperResult {
	
	public CoordinatesRequestResults[] results;
	
	
	
	@Override
	public String toString() {
		return "WrapperResult [results=" + Arrays.toString(results)
				+ ", status=" + status + "]";
	}


	public WrapperCoordinatesResult(CoordinatesRequestResults[] results, String status) {
		super(status);
		this.results = results;
	}
	
	

}
