package sopraturage.util;

public class Time {
	
	
	public static String convertToTime(long longVal){
	    int hours = (int) longVal / 3600;
	    int remainder = (int) longVal - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;
	    
	    return ((hours == 0) ? "" : hours + " hours ") + ((mins == 0) ? "" : mins + " minutes ") + ((secs == 0) ? "" : secs + " seconds");
	}
	
	/*
	public static void main(String[] args) {
		
		long valeur = 17;
		System.out.println(convertToTime(valeur));
		
	}
	*/
	
}
