package sopraturage.models.tables;

import javax.mail.internet.InternetAddress;

public class User {
	
	private String surname;
	private String name;
	private InternetAddress email;
	private String phone;
	private String password;
	private boolean driver;
	private boolean notification;
	private boolean [] working;
	public User(String surname, String name, InternetAddress email,
			String phone, String password, boolean driver, boolean notification,
			boolean[] working) {
		super();
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.driver = driver;
		this.notification = notification;
		this.working = working;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InternetAddress getEmail() {
		return email;
	}
	public void setEmail(InternetAddress email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDriver() {
		return driver;
	}
	public void setDriver(boolean driver) {
		this.driver = driver;
	}
	public boolean isNotification() {
		return notification;
	}
	public void setNotification(boolean notification) {
		this.notification = notification;
	}
	public boolean[] getWorking() {
		return working;
	}
	public void setWorking(boolean[] working) {
		this.working = working;
	}
	
	
	

}
