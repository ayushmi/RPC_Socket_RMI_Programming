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

	// For database operations
	Connection c = null;		
    Statement stmt = null;

	public Rooms(String fileName) throws RemoteException {

		this.fileName = fileName;

		//Open the databse and create two tables
	    try {
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
	                   "(RoomId INT PRIMARY KEY AUTOINCREMENT     NOT NULL," +
	                   " RoomName           TEXT    NOT NULL, " + 
	                   " RoomCapacity            INT     NOT NULL, " + 
	                   " Location  TEXT NOT NULL)";

		  stmt.executeUpdate(sql);

		  sql = "CREATE TABLE IF NOT EXISTS Reservations " +
		  		"(ResevationId INT PRIMARY KEY AUTOINCREMENT NOT NULL, " +
		  		" RoomId INT NOT NULL,  " +
		  		" RoomName TEXT NOT NULL, " +
		  		" EventDescription TEXT, " +
		  		" ResevedBy TEXT NOT NULL, "+
		  		" StartTime DATETIME NOT NULL)";
		  
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
	public String BookRoom (int roomid, int day , int startTime){
		return("0");
	}
	public String RoomsAvailable () {
		return("0");
	}
	public int RoomCapacity(int roomid){
		String message;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for finding Capcity");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Rooms;" );
			while ( rs.next() ) {
				System.out.println(rs.getInteger("RoomId"));
				if (roomid == rs.getInteger("RoomId")) {
					message = rs.getString("RoomName") + " : " + rs.getInt("RoomCapacity");
				}
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
		return(0);
	} 
	public String SignUp(String login, String password, String name, String designation) {
		String message;
		try {
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

		String message;
		try{
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
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		System.out.println(message);
		return(message);
	}

  	public String CheckRoomAvailability (int roomid, int day , int startTime) {
  		String message;
		try{
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
			message = e.getClass().getName() + ": " + e.getMessage();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			
		}
		System.out.println(message);
  		return("0");
  	}
  	public int[][] RoomTimeTable (int roomid) {
  		int a[][]={{1,1},{2,2}};
  		return(a);
  	}
  	public int AddNewRoom(String roomname, int capacity){
  		return(0);
  	}
}

