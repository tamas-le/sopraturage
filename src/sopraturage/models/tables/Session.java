package sopraturage.models.tables;

import java.sql.Timestamp;

public class Session {
	
	private Timestamp debut;
	private Timestamp fin;
	private TinyUser tinyUser;
	
	
	public Session(Timestamp debut, Timestamp fin, TinyUser tinyUser) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.tinyUser = tinyUser;
	}
	
	public Timestamp getDebut() {
		return debut;
	}
	public void setDebut(Timestamp debut) {
		this.debut = debut;
	}
	public Timestamp getFin() {
		return fin;
	}
	public void setFin(Timestamp fin) {
		this.fin = fin;
	}
	public TinyUser getTinyUser() {
		return tinyUser;
	}
	public void setTinyUser(TinyUser tinyUser) {
		this.tinyUser = tinyUser;
	}
	
	
	@Override
	public String toString() {
		return "Session [debut=" + debut + ", fin=" + fin + ", tinyUser="
				+ tinyUser + "]";
	}
	
	

}
