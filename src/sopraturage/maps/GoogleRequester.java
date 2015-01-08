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

import sopraturage.maps.results.Location;
import sopraturage.maps.results.Results;
import sopraturage.maps.results.WrapperResult;

public class GoogleRequester {

	private static final String APIKEY="AIzaSyAyImkhBKOOBmc3oDTQbW2arRqAtR_JCrE";
	private static final String URLBASE="https://maps.googleapis.com/maps/api/geocode/json?address=";


	public GoogleRequester(){

	}


	private String generateAdressForUrl(String formattedAddress){
		formattedAddress=formattedAddress.replaceAll(" ", "+");
		String url=URLBASE+formattedAddress+"&key="+APIKEY;
		return url;
	}

	private Results[] fetchResults(String url){
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
					System.out.println(out);

					bufferedReader.close();

					Gson gson =new Gson();

					WrapperResult resultat=gson.fromJson(out.toString(),WrapperResult.class );
//					System.out.println(resultat);
					return resultat.results;

				} else {
					System.out.println("Erreur serveur "+statusLine);
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


	public Location getCoordinate(String formattedAdress){

		String url =generateAdressForUrl(formattedAdress);
		
		Results[] results=fetchResults(url);
		
		if (results.length<1){
			return null;
		} else {
			return results[0].geometry.location;
		}
		
	}

	public static void main(String[] args) {
		String test = "883 chemin de la pâle 31620 castelnau d'estrètefonds";
		GoogleRequester requester=new GoogleRequester();
		//System.out.println(requester.generateAdressForUrl(test));
		Location loc=requester.getCoordinate(test);
		System.out.println(loc);
		
	}





}
