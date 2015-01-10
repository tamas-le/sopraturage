package sopraturage;

import sopraturage.models.tables.Address;
import sopraturage.models.tables.PostCode;
import sopraturage.models.tables.Workplace;

public class Recherche {

	private boolean defaultRecherche;
	private Workplace workplace;
	private PostCode pc;

	public enum Driver{
		DRIVER,NOT_DRIVER,DONT_CARE
	}

	private Driver driver;

	public Recherche(boolean defaultRecherche, Workplace workplace,
			PostCode pc, Driver driver) {
		super();
		this.defaultRecherche = defaultRecherche;
		this.workplace = workplace;
		this.pc = pc;
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





	public PostCode getPc() {
		return pc;
	}


	public void setPc(PostCode pc) {
		this.pc = pc;
	}


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String displayRecherche(){
		if (defaultRecherche){
			return "You are looking for people who work at the same place as you";
		} else {
			StringBuilder builder=new StringBuilder();
			switch (this.driver){
			case DONT_CARE:
				builder.append("You are looking for people ");
				break;
			case DRIVER:
				builder.append("You are looking for driver ");
				break;
			case NOT_DRIVER:
				builder.append("You are looking for people who do not drive ");
				break;
			}
			
			if (this.pc!=null){
				builder.append("that live in "+pc.getCity()+" ");
			}
			
			if (this.workplace!=null){
				builder.append("working at "+workplace.getName());
			}
			
			
			return builder.toString();
		}
	}








}
