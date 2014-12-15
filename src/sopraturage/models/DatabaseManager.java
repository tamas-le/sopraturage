package sopraturage.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseManager {

	private static final String url = "jdbc:mysql://localhost:3306/sopraturage";
	private static final String utilisateur = "java";
	private static final String motDePasse = "123";

	private Connection connexion;
	private Statement statement;

	public DatabaseManager(){

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
