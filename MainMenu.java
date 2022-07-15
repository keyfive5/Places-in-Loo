import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JButton postButton = new JButton("Post a Sublet");
    JButton rentButton = new JButton("Rent a Sublet");
    JButton cancelButton = new JButton("Post a Sublet");
    JButton rateButton = new JButton("Rate a Sublet");

    // Intialize
    MainMenu() {
        // Labels and Text Fields
        postButton.setBounds(100,160,200,60);
        postButton.setFocusable(false);
        postButton.addActionListener(this);

        rentButton.setBounds(320,160,200,60);
        rentButton.setFocusable(false);

        cancelButton.setBounds(540,160,200,60);
        cancelButton.setFocusable(false);

        rateButton.setBounds(760,160,200,60);
        rateButton.setFocusable(false);

        frame.add(postButton);
        frame.add(rentButton);
        frame.add(cancelButton);
        frame.add(rateButton);
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
  
        if(e.getSource()==postButton) {
         frame.dispose();
         //NewWindow myWindow = new NewWindow();
        }
        else if(e.getSource()==rentButton){
            frame.dispose();
        }
        else if(e.getSource()==cancelButton){
            frame.dispose();
        }
        else if(e.getSource()==rateButton){
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        MainMenu Menu = new MainMenu();
    }

}
