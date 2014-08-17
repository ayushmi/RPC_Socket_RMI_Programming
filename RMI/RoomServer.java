// Room server class
import java.rmi.*;
import java.rmi.server.*;
public class RoomServer {
   public static void main(String argv[]) {
      if (System.getSecurityManager() == null)
         System.setSecurityManager(new RMISecurityManager());
      try {
          // instantiate the service with the standard
          // message & tell registry
          Naming.rebind("Rooms", new Rooms("Rooms.db"));
          System.out.println("Server is running...");
          
      } catch(Exception e) {
          System.out.println("Room Server failed: " + e);
      }
    }
}