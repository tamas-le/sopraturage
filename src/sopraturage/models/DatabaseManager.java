package sopraturage.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseManager {

	// Pour la base de donnée en local
	private static final String url = "jdbc:mysql://localhost:3306/sopraturage";
	private static final String utilisateur = "java";
	private static final String motDePasse = "123";
	
	//Pour la base de donnée sur le serveur
	public static String urlServ;
	private static final String utilisateurServ = "adminbkQsH15";
	private static final String motDePasseServ = "lGIJM9jHiC8l";

	private Connection connexion;
	private Statement statement;

	public DatabaseManager(){
		//mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
		String dbhost=System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String dbport=System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		
		
		if (dbhost!=null && dbport!=null){
			urlServ="jdbc:mysql://"+dbhost+":"+dbport+"/tomcatsopra";
		}
		

	}

	public void connectoDatabase(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();
		
		} catch ( Exception e ) {
			e.printStackTrace();

		} 


	}
	
	public void connectoDatabaseOnline(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connexion = DriverManager.getConnection( urlServ, utilisateurServ, motDePasseServ );
			statement = connexion.createStatement();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	public ResultSet query(String request){
		ResultSet resultat;
		try{
			resultat=statement.executeQuery(request);
			return resultat;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
