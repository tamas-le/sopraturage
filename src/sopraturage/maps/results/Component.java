package sopraturage.maps.results;

import java.util.Arrays;
import java.util.LinkedList;

public class Component {

	public  String long_name;
	public  String short_name;
	public String[]types;



	public Component(String long_name, String short_name,
			String[]types) {
		super();
		this.long_name = long_name;
		this.short_name = short_name;
		this.types = types;
	}



	@Override
	public String toString() {
		return "Component [long_name=" + long_name + ", short_name="
				+ short_name + ", types=" + Arrays.toString(types) + "]";
	}






}
