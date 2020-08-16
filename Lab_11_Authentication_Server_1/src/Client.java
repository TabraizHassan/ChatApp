import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public Login login;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	public ClientUI chatui;
	public static String username;
	
	public Client() {
		login=new Login(this);
		login.setVisible(true);
		Socket client;
		try {
			client = new Socket("localhost",5000);
			System.out.println("Connected to Server");
			oos=new ObjectOutputStream(client.getOutputStream());
			ois=new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	void sendMessage(Packet packet) {
		username=packet.getUsername();
		
		Thread sender=new Thread(new Runnable() {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
			
			Client cli=new Client();
			//String msg=(String) ois.readObject();
			//System.out.println("MSG From Server: "+msg);
			
			Thread receiver=new Thread(new Runnable() {
				
				@Override
				public void run() {
				
					try {
						while(true) {
							Packet msg=(Packet) cli.ois.readObject();
							System.out.println("MSG received in client: "+msg.getMsg());
							if(msg.getMsg().equals("OK")) {
								System.out.println("INSIDE CHECKING");
								cli.login.dispose();
								cli.chatui=new ClientUI(Client.username, cli);
								cli.chatui.setVisible(true);
							}
							if(msg.getType()==PacketType.LOGIN || msg.getType()==PacketType.SIGN_UP)
								cli.login.showErrorMessage(msg.getMsg());
							else if(msg.getType()==PacketType.MSG) {
								cli.chatui.addMessage(msg.getMsg(),"Sever: ");
							}
						}
					
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			receiver.start();
	}
}
