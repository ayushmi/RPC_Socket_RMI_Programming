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

	public Rooms(String fileName) throws RemoteException {

		this.fileName = fileName;

		//Open the databse
		Connection c = null;
    	try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + fileName);
	    }catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");

	}

	/* Need to define the following functions:*/
	public String BookRoom (int roomid, int day , int startTime){
		return("0");
	}
	public String RoomsAvailable () {
		return("0");
	}
	public int RoomCapacity(int roomid){
		return(0);
	} 
	public String SignUp(String login, String password, String name, String designation) {
		return("0");
	}
	public String Login() {
		return("0");
	}
  	public String CheckRoomAvailability (int roomid, int day , int startTime) {
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

