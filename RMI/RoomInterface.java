/*
	Defining RoomInterface Class in this file,
	This defines a remote interface, 
	that describes the remote methods.
*/
import java.rmi.*

public interface RoomInterface extends Remote{
	
	public String BookRoom (int roomid, int day , int startTime) throws RemoteException;
	public List RoomsAvailable () throws RemoteException;
	public int RoomCapacity(int roomid) throws RemoteException;
	public String SignUp(String login, String password, String name, String designation) throws RemoteException;
	public String Login() throws RemoteException;
  	public String CheckRoomAvailability (int roomid, int day , int startTime) throws RemoteException;
  	public int[][] RoomTimeTable (int roomid) throws RemoteException;
  	public AddNewRoom(String roomname, int capacity) throws RemoteException;

}
