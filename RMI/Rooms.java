/*
	This file defines implementation of remote methods
	through a class called Rooms. It extends
	UnicastRemoteObject which provides point to point
	TCP support needed for creating and exporting remote
	objects.
*/

import java.rmi.*
import java.rmi.server.*;
import java.sql.*;

public class Rooms extends UnicastRemoteObject implements RoomInterface {

	/*private String [] RoomNames;
	private int [] RoomIds;
	private TimeTable [] RoomsTimeTable;
	private String [] UserIds;
	private String [] Passwords;
	*/

	public int StartServer(String fileName)
	{

		//Open the databse
		Connection c = null;
    	try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:rooms.db");
	    }catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");

	    
	}

	/* Need to define the following functions:
	public String BookRoom (int roomid, int day , int startTime)
	public List RoomsAvailable () 
	public int RoomCapacity(int roomid) 
	public String SignUp(String login, String password, String name, String designation) 
	public String Login() 
  	public String CheckRoomAvailability (int roomid, int day , int startTime) 
  	public int[][] RoomTimeTable (int roomid) 
  	public AddNewRoom(String roomname, int capacity) */
}

