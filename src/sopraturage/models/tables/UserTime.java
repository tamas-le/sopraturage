package sopraturage.models.tables;

public class UserTime  {
	
	private Long time;
	private User user;
	
	



	public UserTime(Long time, User user) {
		super();
		this.time = time;
		this.user = user;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "UserTime [time=" + time + ", user=" + user.getEmail() + "]";
	}
	
	
	
	

}
