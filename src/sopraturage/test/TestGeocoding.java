package sopraturage.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import sopraturage.maps.results.WrapperResult;

import com.google.gson.Gson;

public class TestGeocoding {
	
	public static void main(String[] args) {
		final String SERVER_URL="https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyAyImkhBKOOBmc3oDTQbW2arRqAtR_JCrE";

		
		try {
			//Create an HTTP client
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(SERVER_URL);

			//Perform the request and check the status code
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();

				try {


					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
					StringBuilder out = new StringBuilder();
					String line;

					while ((line = bufferedReader.readLine()) != null) 
					{
						out.append(line);
					}
					System.out.println(out);
					
					bufferedReader.close();
					
					
					Gson gson =new Gson();
					
					WrapperResult resultat=gson.fromJson(out.toString(),WrapperResult.class );
					
					System.out.println(resultat);
					
					
					
					

				} catch (Exception ex) {
					//Log.e(TAG, "Failed to parse JSON due to: " + ex);
					System.out.println("Soucis avec JSON coco"+ex);
				}

			} else {
				//Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
				System.out.println("Le server a repondu avec le status :"+statusLine.getStatusCode());
			}
		} catch(Exception ex) {
			//Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
			System.out.println("Echec HTTP POST à cause de : "+ex);
		}
		
		
		
	}

}
