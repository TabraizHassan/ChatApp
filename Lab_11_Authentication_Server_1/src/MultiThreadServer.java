import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MultiThreadServer extends Thread {

	private Socket client;
	private DBManager db;
	ObjectOutputStream oos;
	ServerChatUI ui;

	public MultiThreadServer(Socket client) {
		this.client = client;
		db = new DBManager();
	}

	private void printUsers() {
		for (User u : Server.names) {
			System.out.println(u.getUser() + " : " + u.getPassword());
		}
	}

	private String valideSignUP(String username, String password) {
		if (username != null && username.length() >= 3) {
			if (password != null && password.length() >= 5) {
				User u = new User(username, password);
				Server.names.add(u);
				return "USER ADDED";
			}
			return "INVALID PASSWORD";
		}
		return "INVALID USERNAME";
	}

	public String validateLogin(String username, String password) {

		return db.checkUser(username, password);

		/*
		 * System.out.println("validate Login received: " + username + "  " + password);
		 * for (int i = 0; i < Server.names.size(); i++) { User u = Server.names.get(i);
		 * if (username.equalsIgnoreCase(u.getUser()) &&
		 * password.equals(u.getPassword())) { return "OK"; } } return "INVALID USER";
		 */
	}

	public void run() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
			while (true) {
				Packet packet = (Packet) ois.readObject();
				System.out.println("MSG From Client of type " + packet.getType());
				String responce = "";
				switch (packet.getType()) {
				case LOGIN:
					printUsers();
					responce = validateLogin(packet.getUsername(), packet.getPassword());
					if (responce.equals("OK")) {
						startUI(packet.getUsername());
					}
					Packet p = new Packet(PacketType.LOGIN, responce);
					sendMessage(p);
					break;
				case SIGN_UP:

					responce = valideSignUP(packet.getUsername(), packet.getPassword());
					Packet p2 = new Packet(PacketType.SIGN_UP, responce);
					if (responce.equals("OK")) {
						startUI(packet.getUsername());
					}
					sendMessage(p2);
					break;
				case MSG:
					db.addMessage(packet);
					ui.addMessage(packet.getMsg(), ui.getTitle());

				default:
					responce = "ERROR";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startUI(String user) {
		ui = new ServerChatUI(user, this);
		ui.setVisible(true);
	}

	public void sendMessage(Packet packet) {
		// TODO Will do it soon
		Thread sender = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					oos.writeObject(packet);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		sender.start();

	}
}
