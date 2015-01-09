package sopraturage.maps.results;

import java.util.Arrays;


public class CoordinatesRequestResults extends Results{

	public Component [] address_components;
	public String formatted_address;
	public Geometry geometry;
	public String [] types;
	
	
	
	public CoordinatesRequestResults(Component [] address_components,
			String formatted_address, Geometry geometry,
			String [] types) {
		super();
		this.address_components = address_components;
		this.formatted_address = formatted_address;
		this.geometry = geometry;
		this.types = types;
	}



	@Override
	public String toString() {
		return "Results [address_components="
				+ Arrays.toString(address_components) + ", formatted_address="
				+ formatted_address + ", geometry=" + geometry + ", types="
				+ Arrays.toString(types) + "]";
	}


	
	
	
	
	
}
