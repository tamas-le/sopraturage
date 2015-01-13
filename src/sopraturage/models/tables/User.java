package sopraturage.models.tables;

import java.util.Arrays;

import javax.mail.internet.InternetAddress;

public class User {
	
	private String surname;
	private String name;
	private String email;
	private String phone;
	private String password;
	private boolean driver;
	private boolean notification;
	private boolean [] working;
	private int worplaceId;
	private int homeId;
	private int userId;
	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getWorplaceId() {
		return worplaceId;
	}


	public void setWorplaceId(int worplaceId) {
		this.worplaceId = worplaceId;
	}



	
	
	public int getHomeId() {
		return homeId;
	}


	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}


	@Override
	public String toString() {
		return "User [surname=" + surname + ", name=" + name + ", email="
				+ email + ", phone=" + phone + ", password=" + password
				+ ", driver=" + driver + ", notification=" + notification
				+ ", working=" + Arrays.toString(working) + ", worplaceId="
				+ worplaceId + ", homeId=" + homeId + "]";
	}


	public User(String surname, String name, String email,
			String phone, String password, boolean driver, boolean notification,
			boolean[] working,int id,int hid) {
		super();
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.driver = driver;
		this.notification = notification;
		this.working = working;
		this.worplaceId=id;
		this.homeId=hid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
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


	@Override
	public boolean equals(Object obj) {
		User another=(User)obj;
		return this.userId==another.getUserId();
	}
	

	
	
	
	
	

}
