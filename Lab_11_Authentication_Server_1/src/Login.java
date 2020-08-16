import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel lblErrorMSG;
	Client client;


	/**
	 * Create the frame.
	 */
	public Login(Client client) {
		this.client=client;
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(131, 115, 175, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 159, 175, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(176, 221, 89, 23);
		contentPane.add(btnNewButton);
		
		lblErrorMSG = new JLabel("");
		lblErrorMSG.setForeground(Color.RED);
		lblErrorMSG.setBounds(131, 190, 165, 14);
		contentPane.add(lblErrorMSG);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblErrorMSG.setText("");
				String user=textField.getText();
				String password=passwordField.getText();
				
				Packet packet=new Packet(PacketType.LOGIN,user,password);
				client.sendMessage(packet);
				System.out.println("MSG sent to client");
				
			}
		});
		
	}
	
	public void showErrorMessage(String msg) {
		lblErrorMSG.setText(msg);
	}
}
