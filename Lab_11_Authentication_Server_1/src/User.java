
public class User {
	
	private String userName;
	private String password;
	
	
	public User(String user, String password) {
		this.userName = user;
		this.password = password;
	}


	public String getUser() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
