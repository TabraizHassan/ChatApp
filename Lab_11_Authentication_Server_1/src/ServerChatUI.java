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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;








import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;import javax.swing.*;





public class ServerChatUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	JButton btnSend;
	String user;
	MultiThreadServer server;
	private JLabel lblStatus;

	Boolean typing;
	

	/**
	 * Create the frame.
	 */
	public ServerChatUI(String user,MultiThreadServer server) {
		this.user=user;
		this.server=server;
		setTitle("Server: "+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		textField.setBounds(new Rectangle(0, 0, 500, 0));
		panel.add(textField);
		textField.setColumns(30);
		
		scrollPane = new JScrollPane();
		
		textArea = new JTextArea();
		scrollPane.setColumnHeaderView(textArea);
		
		btnSend = new JButton("Send");
		btnSend.setBackground(Color.RED);
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String text=textField.getText();
				addMessage(text,"Me ");
				Packet packet=new Packet(PacketType.MSG,text);
				server.sendMessage(packet);
			}
			
		});
		
		lblStatus = new JLabel("status");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(186)
							.addComponent(lblStatus)))
					.addContainerGap())
		);
		
		
		
		
		
		
		 Timer t = new Timer(1, new ActionListener(){
	           public void actionPerformed(ActionEvent ae){
	               if(!typing){
	                   lblStatus.setText("Active Now");
	               }
	           }
	       });
		
		
		
		
		 t.setInitialDelay(2000);
		 
		 
		 
	
		
		
		
		
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
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
	       
		
		
		
		
		
		
		
		
		
		//textField.addKeyListener(new KeyListener() {
		//	@Override
		//	public void keyTyped(KeyEvent arg0) {
		/*		// TODO Auto-generated method stub
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
		
	}
	
	public void addMessage(String msg,String sender) {
		
		textArea.setText(textArea.getText()+"\n"+sender+":"+msg);
	}
}
