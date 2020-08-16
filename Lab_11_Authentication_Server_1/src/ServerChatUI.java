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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class ServerChatUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	JButton btnSend;
	String user;
	MultiThreadServer server;

	

	/**
	 * Create the frame.
	 */
	public ServerChatUI(String user,MultiThreadServer server) {
		this.user=user;
		this.server=server;
		setTitle("Server: "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(5, 322, 440, 36));
		
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		textField.setBounds(new Rectangle(0, 0, 500, 0));
		panel.add(textField);
		textField.setColumns(30);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 440, 317);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnSend = new JButton("Send");
		scrollPane.setRowHeaderView(btnSend);
		btnSend.setBackground(Color.RED);
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String text=textField.getText();
				addMessage(text,"Server");
				Packet packet=new Packet(PacketType.MSG,text);
				server.sendMessage(packet);
			}
			
		});
		
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void addMessage(String msg,String sender) {
		
		textArea.setText(textArea.getText()+"\n"+sender+": "+msg);
	}

}
