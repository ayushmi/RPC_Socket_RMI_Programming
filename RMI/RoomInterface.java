/*
	Defining RoomInterface Class in this file,
	This defines a remote interface, 
	that describes the remote methods.
*/
import java.rmi.*;

public interface RoomInterface extends Remote{
	

	public String BookRoom (int roomid,String login, String description, int dd, int mm, int yy, int startTime1 , int startTime2) throws RemoteException;
	public String RoomsAvailable () throws RemoteException;
	public int RoomCapacity(int roomid) throws RemoteException;
	public String SignUp(String login, String password, String name, String designation) throws RemoteException;
	public String Login(String login, String password) throws RemoteException;
  	public String CheckRoomAvailability (int roomid, int dd, int mm, int yy, int startTime1, int startTime2) throws RemoteException;
  	public int[][] RoomTimeTable (int roomid) throws RemoteException;
  	public String AddNewRoom(String roomname,  String location, int capacity) throws RemoteException;

}
