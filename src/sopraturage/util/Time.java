package sopraturage.util;

public class Time {
	
	
	public static String convertToTime(long longVal){
	    int hours = (int) longVal / 3600;
	    int remainder = (int) longVal - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;

	   String retour=hours + " hours " + mins + " minutes " + secs + " seconds";

	   return retour;
	    
	
	}
	
	
	public static void main(String[] args) {
		
		long valeur = 43534;
		System.out.println(convertToTime(valeur));
		
	}
	
	
}
