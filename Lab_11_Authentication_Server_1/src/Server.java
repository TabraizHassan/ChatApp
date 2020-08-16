import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static ArrayList<User> names = new ArrayList<>();

	public Server() {
		names.add(new User("AliAwan", "123456"));
		names.add(new User("Ajeeb", "ajeeb"));
		names.add(new User("Najeeb", "123456"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Server server = new Server();

			ServerSocket ss = new ServerSocket(5000);
			System.out.println("Server Listening...");

			while(true) {
				Socket client = ss.accept();
				System.out.println("Client Connected");
				MultiThreadServer thread=new MultiThreadServer(client);
				thread.start();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
