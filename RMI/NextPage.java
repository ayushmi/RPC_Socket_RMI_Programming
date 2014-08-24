import javax.swing.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

class NextPage extends JFrame
{
  JButton bookRoom,roomCapacity,roomTimeTable,checkRoomAvailability,addNewRoom;
  
  RoomInterface  Rooms;
  NextPage(String st)
  {
    try{
      Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");
      String rl = Rooms.RoomsAvailable();
      String lines[] = rl.split("\\r?\\n");
      JLabel roomList[] = new JLabel[lines.length];
      for (int i=0; i<lines.length ;i++ ) {
        roomList[i] = new JLabel(lines[i]);
        roomList[i].setBounds(10,130+30*i,500,20);
        add(roomList[i]);
      }
    }
    catch (Exception e) {
      System.out.println("Roomclient exception: " + e);
    }
    setLayout(null);

    setFocusable(true);// INSERT THIS
    setBackground(Color.green);
    setDefaultCloseOperation(javax.swing. WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Welcome");
    JLabel lab=new JLabel("Welcome "+st);
    lab.setBounds(10,10,500,20);
    add(lab);
    bookRoom = new JButton("Book Room");
    roomCapacity = new JButton("Check Room Capacity");
    addNewRoom = new JButton("Add A new Room");
    checkRoomAvailability = new JButton("Check Room Availability");
    roomTimeTable = new JButton("Check Room Time Table");
    bookRoom.setBounds(400,130,500,20);
    roomCapacity.setBounds(400,160,500,20);
    addNewRoom.setBounds(400,190,500,20);
    checkRoomAvailability.setBounds(400,220,500,20);
    roomTimeTable.setBounds(400,250,500,20);
    add(bookRoom);
    add(roomCapacity);
    add(addNewRoom);
    add(checkRoomAvailability);
    setVisible(true);
    setSize(1024,768);

    setSize(1024, 768);
  }
 }