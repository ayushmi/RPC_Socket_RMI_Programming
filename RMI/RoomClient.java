// Room client class
import java.rmi.*;
public class RoomClient {

   public static void main(String args[]) {
      
      try {
          RoomInterface Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");      
      } catch (Exception e) {
          System.out.println("Roomclient exception: " + e);
      }
	 } 

}