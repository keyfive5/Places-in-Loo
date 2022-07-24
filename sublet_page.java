import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class sublet_page extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
   
    JLabel title = new JLabel("For Rent");
    String rentals [] = {"24 Hickory Street", "35 Lester", "224 University Avenue","232 Weber Street"};
    JList list = new JList<>(rentals);
    JScrollPane scroll = new JScrollPane(list);
    JButton get_post = new JButton("Get Post");    
   
    
    // Intialize
    sublet_page() {
        
        title.setFont(new Font("Calibri", Font.BOLD, 45));
        title.setBounds(120,10,1000,60);
        title.setFocusable(false);
        // Labels and Text Fields
        list.setBounds(50, 75, 300, 50);
        scroll.setBounds(50, 75, 300, 50);
        get_post.setBounds(50, 300, 100, 20);
        get_post.addActionListener(this);
       
        frame.add(title);
        frame.add(scroll);
        frame.add(get_post);

       
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get_post){
            System.out.println(list.getSelectedValue());
        }
  
       
    }

    public static void main(String[] args) {
        sublet_page Menu = new sublet_page();
    }

}
