import javax.swing.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

class NextPage extends JFrame
{
  RoomInterface  Rooms; 
  NextPage(String st)
  {
    try{
      Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");
    }
    catch (Exception e) {
      System.out.println("Roomclient exception: " + e);
    }
    setLayout(null);

    setFocusable(true);// INSERT THIS
    setBackground(Color.green);
    setDefaultCloseOperation(javax.swing. WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Welcome");
    JLabel lab=new JLabel("Welcome  "+st);

    lab.setBounds(10,10,500,20);
    add(lab);

    setSize(1024, 768);
  }
 }