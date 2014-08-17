/*
	This file defines implementation of remote methods
	through a class called Rooms. It extends
	UnicastRemoteObject which provides point to point
	TCP support needed for creating and exporting remote
	objects.
*/

import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;

public class Rooms extends UnicastRemoteObject implements RoomInterface {

	/*private String [] RoomNames;
	private int [] RoomIds;
	private TimeTable [] RoomsTimeTable;
	private String [] UserIds;
	private String [] Passwords;
	*/
	private String fileName;		//Database file

	private int globalrservationid=1;
	private int globalroomid =1;

	public Rooms(String fileName) throws RemoteException {

		this.fileName = fileName;

		//Open the databse and create two tables
	    try {
	    	// For database operations
			Connection c = null;		
		    Statement stmt = null;
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE IF NOT EXISTS Users " +
	                   "(LoginID TEXT PRIMARY KEY     NOT NULL," +
	                   " NAME           TEXT    NOT NULL, " + 
	                   " Privilages            INT     NOT NULL, " + 
	                   " Password 		TEXT NOT NULL, " +
	                   " Designation  CHAR(50) NOT NULL)"; 
	      stmt.executeUpdate(sql);

	      sql = "CREATE TABLE IF NOT EXISTS Rooms " +
	                   "(RoomId INTEGER PRIMARY KEY ," +
	                   " RoomName           TEXT    NOT NULL, " + 
	                   " RoomCapacity            INTEGER     NOT NULL, " + 
	                   " Location  TEXT NOT NULL)";

		  stmt.executeUpdate(sql);

		  sql = "CREATE TABLE IF NOT EXISTS Reservations " +
		  		"(ResevationId INTEGER PRIMARY KEY, " +
		  		" RoomId INTEGER NOT NULL,  " +
		  		" EventDescription TEXT, " +
		  		" ReservedBy TEXT NOT NULL, "+
		  		" DD INTEGER NOT NULL," +
		  		" MM INTEGER NOT NULL," +
		  		" YY INTEGER NOT NULL," +
		  		" StartTime1 INTEGER NOT NULL," +
		  		" StartTime2 INTEGER NOT NULL)";
		  
		  stmt.executeUpdate(sql);

	      stmt.close();
	      c.close();
	    
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");

	}

	/* Need to define the following functions:*/
	public String BookRoom (int roomid,String login, String description, int dd, int mm, int yy, int startTime1 , int startTime2){
		String message="-1";
		int flag=0;
		try{
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for Checking Room Booking");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Reservations;" );
			
			while ( rs.next() ) {
				if (rs.getInt("RoomId") == roomid &&rs.getInt("DD") == dd && rs.getInt("MM") == mm && rs.getInt("YY") == yy && rs.getInt("StartTime1") == startTime1 && rs.getInt("StartTime2") == startTime2) {
					message = "Room is not available for booking for this slot.";	
					flag=1;	
				}
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			flag =1;
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		if (flag == 0) {
			try{
				// For database operations
	Connection c = null;		
    Statement stmt = null;
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
				c.setAutoCommit(false);
				System.out.println("Opened database successfully for Room Booking Step 2");

				stmt = c.createStatement();
				String sql = "INSERT INTO Reservations (ResevationId, RoomId,EventDescription,ReservedBy,DD,MM,YY,StartTime1, StartTime2) " +
							   "VALUES ("+globalrservationid+","+roomid+",'"+description+"','"+login+"',"+dd+","+mm+","+yy+","+startTime1+","+startTime2+");"; 
				stmt.executeUpdate(sql);
				stmt.close();
				c.commit();
				c.close();
				message = "Resevation successfully";
				globalrservationid = globalrservationid +1;
				}catch (Exception e)
				{
					message = e.getClass().getName() + ": " + e.getMessage();
					System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				}
		}
		System.out.println(message);
		return(message);
	}
	public String RoomsAvailable () {
		String message="RoomID\tRoom Name\tRoom Location\tRoom Capacity\n";
		try{// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for listing rooms");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Rooms;" );
			while ( rs.next() ) {
				message = message + rs.getInt("RoomId") +"\t" + rs.getString("RoomName") + "\t" +
									rs.getString("Location") + "\t" + rs.getInt("RoomCapacity") + "\n";
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println(message);
		return(message);
	}
	public int RoomCapacity(int roomid){
		String message="";
		int returnVal=-1;
		try{
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for finding Capcity");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Rooms;" );
			while ( rs.next() ) {
				System.out.println(rs.getInt("RoomId"));
				if (roomid == rs.getInt("RoomId")) {
					message = "Capacity is " + rs.getInt("RoomCapacity");
					returnVal = rs.getInt("RoomCapacity");
				}
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			returnVal = -1;
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		System.out.println(message);
		return(returnVal);
	} 
	public String SignUp(String login, String password, String name, String designation) {
		String message;
		try {
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			int privilages;
			if (designation.equals("faculty") || designation.equals("staff")) {
				privilages = 1;
			}
			else privilages = 0;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for SignUp");

			stmt = c.createStatement();
			String sql = "INSERT INTO Users (LoginID,NAME,Privilages,Password,Designation) " +
						   "VALUES ('"+login+"','"+name+"',"+privilages+",'"+password+"','"+designation+"');"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
			message = "Records created successfully";
		} catch ( Exception e ) {
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		System.out.println("Records created successfully");
		return(message);
	}

	//Login Method returns -1 on unsuccessful login, o.w. returns privilages and other informaiton in string.
	public String Login(String login, String password) {

		String message="-1";
		try{
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for Login");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Users;" );
			int count=0;
			while ( rs.next() ) {
				count++;
				System.out.println(rs.getString("LoginID"));
				if (login.equals(rs.getString("LoginID")) && password.equals(rs.getString("Password"))) {
					message = rs.getString("NAME") + " " + rs.getString("Privilages") + " " + rs.getString("Designation");
				}
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			//message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		System.out.println(message);
		return(message);
	}

  	public String CheckRoomAvailability (int roomid, int dd, int mm, int yy, int startTime1, int startTime2) {
  		String message="-1";
  		int flag=0;
		try{
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for Checking Room Availability");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Reservations;" );
			
			while ( rs.next() ) {
				if (rs.getInt("RoomId") == roomid && rs.getInt("DD") == dd && rs.getInt("MM") == mm && rs.getInt("YY") == yy && rs.getInt("StartTime1") == startTime1 && rs.getInt("StartTime2") == startTime2) {
					message = "Room is not available for booking for this slot.";	
					flag=1;	
				}
			}
			rs.close();
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			flag =1;
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		if (flag == 0) {
			message = "Room is available for booking in this slot.";
		}
		System.out.println(message);
  		return(message);
  	}
  	public int[][] RoomTimeTable (int roomid) {
  		int a[][]={{1,1},{2,2}};
  		return(a);
  	}
  	public String AddNewRoom(String roomname,  String location, int capacity){
  		String message;
		try {
			// For database operations
	Connection c = null;		
    Statement stmt = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for Adding Rooms");

			stmt = c.createStatement();
			String sql = "INSERT INTO Rooms (RoomID,RoomName,Location,RoomCapacity) " +
						   "VALUES ("+globalroomid+",'"+roomname+"','"+location+"',"+capacity+");"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
			message = "Room Added successfully";
			globalroomid = globalroomid+1;
		} catch ( Exception e ) {
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );			
		}
		System.out.println("Room Added successfully");
		return(message);
  	}
}

