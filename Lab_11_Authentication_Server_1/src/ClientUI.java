import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	JButton btnSend;
	String user;
	Client client;

	

	/**
	 * Create the frame.
	 */
	public ClientUI(String user,Client client) {
		this.user=user;
		this.client=client;
		
		setTitle("Client: "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(5, 234, 440, 39));
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(new Rectangle(6, 5, 279, 19));
		panel.add(textField);
		textField.setColumns(30);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(284, 2, 139, 29);
		panel.add(btnSend);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 440, 229);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String text=textField.getText();
				addMessage(text,"Me:");
				Packet packet=new Packet(PacketType.MSG,text);
				client.sendMessage(packet);
			}
			
		});
		
	}
	
	public void addMessage(String msg,String sender) {
		
		textArea.setText(textArea.getText()+"\n"+sender+" "+msg);
	}

}
