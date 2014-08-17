import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

class HomePage extends JFrame{
 
 JButton Login;
 JButton SignUp;
 JLabel label1;
  HomePage(){
  setTitle("Home Page");
  setLayout(null);
  label1 = new JLabel();
  label1.setText("Welcome to Room Booking portal:");
  Login=new JButton("Login");
  SignUp = new JButton("SignUp");
  label1.setBounds(350,100,100,20);
  Login.setBounds(350,130,100,20);
  SignUp.setBounds(450,160,100,20);
  add(label1);
  add(Login);
  add(SignUp);
  setVisible(true);
  setSize(1024,768);

  Login.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent ae){
      new LoginDemo();
}
 });
  SignUp.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent ae){
      new SignUpDemo();
}
 });
  }
  public static void main(String arg[]){
  new HomePage();
}
}