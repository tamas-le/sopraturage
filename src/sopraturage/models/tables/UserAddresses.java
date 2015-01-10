package sopraturage.models.tables;

public class UserAddresses {
	private User user;
	private Address home;
	private Workplace workplace;
	public UserAddresses(User user, Address home, Workplace workplace) {
		super();
		this.user = user;
		this.home = home;
		this.workplace = workplace;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getHome() {
		return home;
	}
	public void setHome(Address home) {
		this.home = home;
	}
	public Workplace getWorkplace() {
		return workplace;
	}
	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}
	@Override
	public String toString() {
		return "UserAddresses [user=" + user + ", home=" + home
				+ ", workplace=" + workplace + "]";
	}
	
	
	
	

}
