// Room client class
import java.rmi.*;

public class RoomClient {

   public static void main(String args[]) {
      
      try {
            String result;
           RoomInterface Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");
           System.out.println("Signing In....");
           result = Rooms.Login("rkghosh","rkghosh");
           System.out.println("Signing In Complete: " + result);
           

           // System.out.println("Adding Room CS101");
           // result = Rooms.AddNewRoom("CS101","HR Kadim Building Ground Floor",100);
           // System.out.println(result);
           // System.out.println("Adding Room CS102");
           // result = Rooms.AddNewRoom("CS102","HR Kadim Building Ground Floor",50);
           // System.out.println(result);
           // System.out.println("Adding Room CS103");
           // result = Rooms.AddNewRoom("CS103","HR Kadim Building Ground Floor",30);
           // System.out.println(result);
           // System.out.println("Adding Room RM101");
           // result = Rooms.AddNewRoom("RM101","Rajeev Motwani Building Ground Floor",250);
           // System.out.println(result);


           System.out.println("Getting Room List...");
           result = Rooms.RoomsAvailable();
           System.out.println(result);

           System.out.println("Booking RM101 for 22/8/2014 10:00");
           result = Rooms.BookRoom(4,"rkghosh", "CS632A - Lecture",  22,  8,  2014,  10 ,  0);
           System.out.println(result);

           System.out.println("Checking Availability for Booking RM101 for 22/8/2014 10:00");
           result = Rooms.CheckRoomAvailability(4,  22,  8,  2014,  10 ,  0);
           System.out.println(result);

           // System.out.println("Checking Capacity of  RM101 ");
           // result = Rooms.RoomCapacity(4);
           // System.out.println("Room Capacity of RM101 is : " + result);

      } catch (Exception e) {
          System.out.println("Roomclient exception: " + e);
      }
	 } 

}