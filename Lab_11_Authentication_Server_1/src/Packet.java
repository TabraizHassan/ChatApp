import java.io.Serializable;

public class Packet implements Serializable{
	private PacketType type;
	private String username;
	private String password;
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Packet(PacketType type, String username, String password) {
		this.type = type;
		this.username = username;
		this.password = password;
	}
	public Packet(PacketType type, String msg) {
		this.type = type;
		this.msg=msg;
	}

	public PacketType getType() {
		return type;
	}

	public void setType(PacketType type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
