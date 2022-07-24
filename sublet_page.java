import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class sublet_page extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
   
    JLabel title = new JLabel("For Rent");
    String rentals [] = {"id:1, 24 Hickory Street", "id:2, 35 Lester", "id:3, 224 University Avenue","id:4, 232 Weber Street"};
    JList list = new JList<>(rentals);
    JLabel info = new JLabel();
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
        info.setBounds(200, 200, 100, 100);
       
        frame.add(title);
        frame.add(scroll);
        frame.add(get_post);
        frame.add (info);

       
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get_post){
            //frame.remove(info);
            //System.out.println(list.getSelectedValue());
            int post_location = list.getSelectedIndex();
            info.setText(rentals[post_location]);
            info.setBounds(50, 100, 1000, 100);
            info.setFont(new Font("Verdana", Font.PLAIN, 20));;
            info.setHorizontalAlignment(JLabel.LEADING);
            frame.add(info);
        }
  
       
    }

    public static void main(String[] args) {
        sublet_page Menu = new sublet_page();
    }

}
