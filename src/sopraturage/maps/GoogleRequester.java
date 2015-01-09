package sopraturage.maps;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import sopraturage.maps.results.CoordinatesRequestResults;
import sopraturage.maps.results.LatLng;
import sopraturage.maps.results.WrapperCoordinatesResult;
import sopraturage.maps.results.WrapperDirectionsResult;

public class GoogleRequester {

	private static final String APIKEY="AIzaSyAyImkhBKOOBmc3oDTQbW2arRqAtR_JCrE";
	private static final String URL_BASE_COORDINATE_REQUEST="https://maps.googleapis.com/maps/api/geocode/json?address=";
	private static final String URL_BASE_DIRECTION_REQUEST="https://maps.googleapis.com/maps/api/directions/json?";

	
	

	public GoogleRequester(){

	}


	private String generateAddressForUrl(String formattedAddress){
		formattedAddress=formattedAddress.replaceAll(" ", "+");
		String url = URL_BASE_COORDINATE_REQUEST + formattedAddress + "&key="+APIKEY;
		return url;
	}
	
	private String generateDirectionRequestURL(LatLng departure_coordinates, LatLng arrival_coordinates){
		return URL_BASE_DIRECTION_REQUEST 
				+ "origin=" + departure_coordinates.lat + "+" + departure_coordinates.lng 
				+ "&destination=" + arrival_coordinates.lat + "+" + arrival_coordinates.lng 
				+ "&key=" + APIKEY;
	}
	
	private String generateDirectionRequestURL(LatLng departure_coordinates, LatLng arrival_coordinates, LatLng waypoint){
		return URL_BASE_DIRECTION_REQUEST 
				+ "origin=" + departure_coordinates.lat + "+" + departure_coordinates.lng 
				+ "&destination=" + arrival_coordinates.lat + "+" + arrival_coordinates.lng 
				+ "&waypoints=via:" + waypoint.lat + "+" + waypoint.lng 
				+ "&key=" + APIKEY;
	}

	private String fetchResults(String url){
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			try {
				if(statusLine.getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();


					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
					StringBuilder out = new StringBuilder();
					String line;

					while ((line = bufferedReader.readLine()) != null) 
					{
						out.append(line);
					}
					//System.out.println(out);

					bufferedReader.close();

					//Gson gson =new Gson();

					//return gson.fromJson(out.toString(), WrapperResult.class);
					return out.toString();
//					System.out.println(resultat);
					//return resultat.results;

				} else {
					System.out.println("Erreur serveur " + statusLine);
				}
			} catch (Exception e){
				System.out.println("Erreur avec le json");
				e.printStackTrace();
			}

		} catch (Exception e){
			System.out.println("Erreur avec le http get");
			e.printStackTrace();
		}




		return null;
	}


	public LatLng getCoordinate(String formattedAdress){

		String url = generateAddressForUrl(formattedAdress);
		Gson gson = new Gson();
		WrapperCoordinatesResult wrapper_result = gson.fromJson(this.fetchResults(url), WrapperCoordinatesResult.class);
		
		CoordinatesRequestResults[] results = wrapper_result.results;
		
		if (results.length<1){
			return null;
		} else {
			return results[0].geometry.location;
		}
		
	}
	
	
	//Retourne le temps du trajet en seconde en passant par waypoint sans arrêt
	public Long getTravelTime(LatLng origin, LatLng destination, LatLng waypoint) throws Exception{
		
		String url = generateDirectionRequestURL(origin, destination, waypoint);
		//2 legs because there is a Waypoint
		Gson gson = new Gson();
		WrapperDirectionsResult request_output = gson.fromJson(this.fetchResults(url), WrapperDirectionsResult.class);
		
		if (!"OK".equals(request_output.status)){
			throw new Exception("Aucune solution de route trouvé par Google Map.");
		}
		
		return request_output.routes[0].legs[0].duration.value;
	}
	
	//Retourne le temps du trajet en seconde
	public Long getTravelTime(LatLng origin, LatLng destination) throws Exception{
		
		String url = generateDirectionRequestURL(origin, destination);
		Gson gson = new Gson();
		WrapperDirectionsResult request_output = gson.fromJson(this.fetchResults(url), WrapperDirectionsResult.class);
		
		if (!"OK".equals(request_output.status)){
			throw new Exception("Aucune solution de route trouvé par Google Map.");
		}
		
		return request_output.routes[0].legs[0].duration.value;
	}
	
	/*
	public static void main(String[] args) {
		//String test = "883 chemin de la p�le 31620 castelnau d'estr�tefonds";
		GoogleRequester requester = new GoogleRequester();
		//System.out.println(requester.generateAddressForUrl(test));
		//LatLng loc = requester.getCoordinate(test);
		//System.out.println(loc);
		
		LatLng origin = requester.getCoordinate("Pau"),
				 destination = requester.getCoordinate("Toulouse"),
				 waypoint = requester.getCoordinate("Tarbes");
		
		try {
			System.out.println(origin);
			System.out.println(destination);
			System.out.println(waypoint);
			System.out.println(requester.getTravelTime(origin, waypoint));
			System.out.println(requester.getTravelTime(waypoint, destination));
			System.out.println(requester.getTravelTime(origin, destination, waypoint));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	*/



}
