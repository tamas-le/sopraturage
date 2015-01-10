package sopraturage;

import sopraturage.models.tables.Address;
import sopraturage.models.tables.Workplace;

public class Recherche {
	
	private boolean defaultRecherche;
	private Workplace workplace;
	private Address address;
	private boolean driver;
	
	
	public Recherche(boolean defaultRecherche, Workplace workplace,
			Address address, boolean driver) {
		super();
		this.defaultRecherche = defaultRecherche;
		this.workplace = workplace;
		this.address = address;
		this.driver = driver;
	}


	public boolean isDefaultRecherche() {
		return defaultRecherche;
	}


	public void setDefaultRecherche(boolean defaultRecherche) {
		this.defaultRecherche = defaultRecherche;
	}


	public Workplace getWorkplace() {
		return workplace;
	}


	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public boolean isDriver() {
		return driver;
	}


	public void setDriver(boolean driver) {
		this.driver = driver;
	}
	
	
	
	

}
