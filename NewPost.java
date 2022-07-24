import java.awt.*;
import javax.swing.*;
public class NewPost {
JFrame frame = new JFrame();
 JLabel label = new JLabel("Test");
 JLabel title = new JLabel("Post a Sublet");
 JLabel location = new JLabel("Location");
 JLabel sublessor = new JLabel ("Sublessor");
 JLabel duration = new JLabel("Duration");
 JLabel description = new JLabel ("Description");
 JTextField location_txt = new JTextField ("Enter Location");
 JTextField sublessor_txt = new JTextField("Enter Sublessor");
 JTextField duration_txt = new JTextField ("Enter Duration in months");
 JTextField description_txt = new JTextField("Enter Description");
 JButton submit_btn = new JButton ("Submit");
 
 NewPost(){
  
  label.setBounds(0,0,100,50);
  label.setFont(new Font(null,Font.PLAIN,25));

  title.setBounds(125,10,1000,50);
  title.setFont(new Font(null,Font.PLAIN,25));

  location.setBounds(50,50,100,50);
  location.setFont(new Font(null,Font.PLAIN,15));
  location_txt.setBounds(120,65,200,30);

  sublessor.setBounds(50,90,100,50);
  sublessor.setFont(new Font(null,Font.PLAIN,15));
  sublessor_txt.setBounds(120,105,200,30);

  duration.setBounds(50,130,100,50);
  duration.setFont(new Font(null,Font.PLAIN,15));
  duration_txt.setBounds(120,145,200,30);

  description.setBounds(30,170,100,50);
  description.setFont(new Font(null,Font.PLAIN,15));
  description_txt.setBounds(120,185,200,150);

  submit_btn.setBounds(230,340,100,40);


    
  frame.add(title);
  frame.add(location);
  frame.add(sublessor);
  frame.add(duration);
  frame.add(description);
  frame.add(location_txt);
  frame.add(sublessor_txt);
  frame.add(duration_txt);
  frame.add(description_txt);
  frame.add(submit_btn);

  
  //frame.add(label);
  
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setBackground(new Color(173, 216, 230));
  frame.setSize(420,420);
  frame.setLayout(null);
  frame.setVisible(true);
  
 }
 public static void main(String[] args) {
        NewPost post = new NewPost();
    }
}
