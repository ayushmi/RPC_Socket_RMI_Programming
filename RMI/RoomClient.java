// Room client class
import java.rmi.*;

public class RoomClient {

   public static void main(String args[]) {
      
      try {
          RoomInterface Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");
           String result = Rooms.SignUp ("rkghosh","rkghosh","Ratan K Ghosh","faculty");
           System.out.println(result);
          result = Rooms.Login("rkghosh","rkghosh");
          System.out.println(result);     
      } catch (Exception e) {
          System.out.println("Roomclient exception: " + e);
      }
	 } 

}