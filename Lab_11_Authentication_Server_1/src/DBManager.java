import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBManager {

//	The connection string (also called Database URL) specifies the
//	the DBMS we want to connect to, where that DBMS is located
//	(localhost in our case), and the name of the database
	private String connectionString = "jdbc:mysql://localhost/mydatabase";

//	Note that Connection, Statement, and ResultSet, all three are
//	actually interfaces in Java, not classes. So we cannot instantiate
//	these. In fact, the JDBC Driver (MySQL Connector/j in our case)
//	defines the classes which provide implementation of these
//	interfaces. So we use a standard sequence of JDBC method calls (see the
//	constructor implementation below) to get the objects
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	public DBManager() {
		/*
		 * try { // The getConnection() method of the Driver Manager class takes the //
		 * connection string as an argument, besides username and password. // It uses
		 * the Connection implementation provided by Connector/j // to instantiate a
		 * connection object (which knows how to connect to // MySQL). conn =
		 * DriverManager.getConnection(connectionString, "root", "");
		 * 
		 * // Once we have the connection to DBMS, we use it get the Statement //
		 * object, which we'll use to carry out execution of the SQL statement st =
		 * conn.createStatement();
		 * 
		 * // By executing the query (see argument), we get the result of the // query
		 * in a ResultSet object rs = st.executeQuery("select * from users");
		 * 
		 * // Now we iterate over ResultSet to get the data. When we call next(), // it
		 * checks if there's a next row of data, and if there is it moves // the cursor
		 * to that row. You should figure out how the get methods work? while
		 * (rs.next()) { // System.out.println(rs.getString(1)+" " //
		 * +rs.getString(2)+" "); System.out.println(rs.getString(1) + "\t" +
		 * rs.getString(2) + "\t" + rs.getString(3)); // for(int i=1;i<=4; i++) //
		 * System.out.print(rs.getObject(i)+"  "); // System.out.println(); }
		 * 
		 * // Finally, we should close the ResultSet, Statement, and Connection objects
		 * rs.close(); st.close(); conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}

	private void createConnection() {
//		The getConnection() method of the Driver Manager class takes the
//		connection string as an argument, besides username and password.
//		It uses the Connection implementation provided by Connector/j
//		to instantiate a connection object (which knows how to connect to
//		MySQL).
		try {
			conn = DriverManager.getConnection(connectionString, "root", "");

//		Once we have the connection to DBMS, we use it get the Statement
//		object, which we'll use to carry out execution of the SQL statement
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMessage(Packet packet) {
		createConnection();
		
	
		String sql="insert into messages (sender,receiver,msg) values ('"+packet.getUsername()
		+"','Server','"+packet.getMsg()+"')";
		
		try {
			st.execute(sql);
			
			JOptionPane.showMessageDialog(null,"Message Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String checkUser(String user, String password) {
		createConnection();
		
		try {
			rs = st.executeQuery("select * from users");
//		Now we iterate over ResultSet to get the data. When we call next(),
//		it checks if there's a next row of data, and if there is it moves
//		the cursor to that row. You should figure out how the get methods work?
			while (rs.next()) {
				// System.out.println(rs.getString(1)+" "
				// +rs.getString(2)+" ");
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
				
				if(user.equalsIgnoreCase(rs.getString(1))) {
					if(password.equals(rs.getString(2))) {
						return "OK";
					}
					return "INVALID PASSWORD";
				}
				
				
				
//			for(int i=1;i<=4; i++)
//				System.out.print(rs.getObject(i)+"  ");
//			System.out.println();

//				Finally, we should close the ResultSet, Statement, and Connection objects
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "INVALID USERNAME OR PASSSWORD";

	}
}
