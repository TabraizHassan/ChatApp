import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;import javax.swing.*;




public class ClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	JButton btnSend;
	String user;
	Client client;
	Boolean typing;
	private JLabel lblStatus;

	

	/**
	 * Create the frame.
	 */
	public ClientUI(String user,Client client) {
		this.user=user;
		this.client=client;
		
		setTitle("Client: "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(5, 321, 440, 39));
		
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
		scrollPane.setBounds(5, 57, 440, 229);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(204, 18, 61, 16);
		contentPane.add(lblStatus);
		
		
		
		

		 Timer t = new Timer(1, new ActionListener(){
	           public void actionPerformed(ActionEvent ae){
	               if(!typing){
	                   lblStatus.setText("Active Now");
	               }
	           }
	       });
		
		
		
		
		 t.setInitialDelay(2000);
		 
		 
		 
		 
		 textField.addKeyListener(new KeyAdapter(){
	           public void keyPressed(KeyEvent ke){
	               lblStatus.setText("typing...");
	               
	               t.stop();
	               
	               typing = true;
	           }
	           
	           public void keyReleased(KeyEvent ke){
	               typing = false;
	               
	               if(!t.isRunning()){
	                   t.start();
	               }
	           }
	       });
	       
		 
		 
		 
		
		
		
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
