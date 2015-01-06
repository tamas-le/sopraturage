package sopraturage.util;

import java.awt.SystemColor;
import java.sql.Timestamp;

public class TimeStampCut {
	
	private Timestamp timestamp;

	
	
	public TimeStampCut(Timestamp timestamp) {
		super();
		this.timestamp = timestamp;
	}
	
	public String getResult(){
		String timestampString=timestamp.toString();
		String morceau=timestampString.substring(18);
		float v=Float.parseFloat(morceau);
		int value=Math.round(v);
		String resultat=""+timestampString.substring(0, 18)+value;

		return resultat;
	}
	
	
	
	@Override
	public String toString() {
		return timestamp.toString();
	}

	public static void main(String[] args) {
		
		TimeStampCut cut= new TimeStampCut(new Timestamp(System.currentTimeMillis()));
		System.out.println(cut);
		System.out.println(cut.getResult());
		

	}
	
	
	
	

}
