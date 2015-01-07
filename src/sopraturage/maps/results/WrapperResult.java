package sopraturage.maps.results;

import java.util.Arrays;

public class WrapperResult {
	
	public Results[] results;
	public String status;
	
	

	
	
	
	@Override
	public String toString() {
		return "WrapperResult [results=" + Arrays.toString(results)
				+ ", status=" + status + "]";
	}






	public WrapperResult(Results[] results, String status) {
		super();
		this.results = results;
		this.status = status;
	}
	
	

}
