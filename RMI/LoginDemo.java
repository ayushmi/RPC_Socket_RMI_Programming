import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

class LoginDemo extends JFrame{
 
 JButton SUBMIT;
 JLabel label1,label2;
 RoomInterface  Rooms;
 final JTextField  text1,text2;
  LoginDemo(){
    try{
    Rooms = (RoomInterface)Naming.lookup("//localhost/Rooms");
  }
  catch (Exception e) {
          System.out.println("Roomclient exception: " + e);
  }
  setTitle("Login Form");
  setLayout(null);

  setFocusable(true);// INSERT THIS
  setBackground(Color.blue);
  label1 = new JLabel();
  label1.setText("Username:");
  text1 = new JTextField(15);

  label2 = new JLabel();
  label2.setText("Password:");
  text2 = new JPasswordField(15);

  SUBMIT=new JButton("SUBMIT");
  label1.setBounds(350,100,100,20);
  text1.setBounds(450,100,200,20);
  label2.setBounds(350,130,100,20);
  text2.setBounds(450,130,200,20);
  SUBMIT.setBounds(450,160,100,20);
  add(label1);
  add(text1);
  add(label2);
  add(text2);
  add(SUBMIT);

  setVisible(true);
  setSize(1024,768);

  SUBMIT.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent ae){
    String value1=text1.getText();
    String value2=text2.getText();
    String result="-1";
    try{
     result = Rooms.Login(value1,value2);
    }
    catch (Exception e) {
          System.out.println("Roomclient exception: " + e);
      }
    if(value1.equals("") && value2.equals("")) {
      JOptionPane.showMessageDialog(null,"Enter login name or password","Error",JOptionPane.ERROR_MESSAGE);
    }
    else if (result.equals("-1")) {
      JOptionPane.showMessageDialog(null,"Enter login name or password","Error",JOptionPane.ERROR_MESSAGE);
    }
    else  {
      NextPage page=new NextPage(value1);
      page.setVisible(true);
    }
}
 });
  }
}