package sopraturage.models.tables;

public class TinyUser {
	private String email;
	private int id;
	private String surname;
	private String name;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public TinyUser(String email, int id, String surname, String name) {
		super();
		this.email = email;
		this.id = id;
		this.surname = surname;
		this.name = name;
	}
	
	

}
