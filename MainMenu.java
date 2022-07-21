import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JButton postButton = new JButton("Post a Sublet");
    JButton rentButton = new JButton("Rent a Sublet");
    JButton cancelButton = new JButton("Cancel a Sublet");
    JButton rateButton = new JButton("Rate a Sublet");
    JLabel title = new JLabel("Main Page");

    // Intialize
    MainMenu() {
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 45));
        title.setBounds(100,100,1000,60);
        title.setFocusable(false);
        // Labels and Text Fields
        postButton.setBounds(200,160,200,60);
        postButton.setFocusable(false);
        postButton.addActionListener(this);

        rentButton.setBounds(420,160,200,60);
        rentButton.setFocusable(false);
        rentButton.addActionListener(this);

        cancelButton.setBounds(640,160,200,60);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(this);

        rateButton.setBounds(860,160,200,60);
        rateButton.setFocusable(false);
        rateButton.addActionListener(this);

        frame.add(postButton);
        frame.add(rentButton);
        frame.add(cancelButton);
        frame.add(rateButton);
        frame.add(title);
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,500);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
  
        if(e.getSource()==postButton) {
         frame.dispose();
         NewPost post = new NewPost();
        }
        else if(e.getSource()==rentButton){
            frame.dispose();
            Rent rent = new Rent();
        }
        else if(e.getSource()==cancelButton){
            frame.dispose();
            Cancel cancel = new Cancel();
        }
        else if(e.getSource()==rateButton){
            frame.dispose();
            Rate rate = new Rate();
        }
    }

    public static void main(String[] args) {
        MainMenu Menu = new MainMenu();
    }

}
