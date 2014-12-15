import java.util.Arrays;

import javax.mail.internet.InternetAddress;


public class Information {
	private String name;
	private String surname;
	private String password;
	private InternetAddress email;
	private int[] phoneNumber;
	private String workplace;
	private String home;
	private boolean driver;
	private boolean notifications;
	
	
	public Information(String name, String surname, String password,
			InternetAddress email, int[] phoneNumber, String workplace,
			String home, boolean driver, boolean notifications) {
		super();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.workplace = workplace;
		this.home = home;
		this.driver = driver;
		this.notifications = notifications;
	}
	
	


	@Override
	public String toString() {
		return "Information [name=" + name + ", surname=" + surname
				+ ", password=" + password + ", email=" + email
				+ ", phoneNumber=" + Arrays.toString(phoneNumber)
				+ ", workplace=" + workplace + ", home=" + home + ", driver="
				+ driver + ", notifications=" + notifications + "]";
	}




	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public InternetAddress getEmail() {
		return email;
	}


	public void setEmail(InternetAddress email) {
		this.email = email;
	}


	public int[] getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(int[] phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getWorkplace() {
		return workplace;
	}


	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}


	public String getHome() {
		return home;
	}


	public void setHome(String home) {
		this.home = home;
	}


	public boolean isDriver() {
		return driver;
	}


	public void setDriver(boolean driver) {
		this.driver = driver;
	}


	public boolean isNotifications() {
		return notifications;
	}


	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}
	
	
	
	
	
}
