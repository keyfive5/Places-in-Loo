import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TestGUI {
    public static void main(String[] args){
        // make a new frame which is just the window it self and initlize all its things 
        JFrame login = new JFrame();
        login = create_frame();
        

    }

    static JFrame create_frame(){
        // setting up our window it self
        JFrame login  = new JFrame("Login");
        login.setLayout(new BorderLayout());
        login.setSize(400,400);
        
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Jpanel goes ontop of the window and is bassiclly where we add all the buttons and things onto
        JPanel title_pan = new JPanel();
        title_pan.setLayout(new BorderLayout());
        title_pan.setBackground(new Color(173, 216, 230));

        JPanel logPanel = new JPanel();
        logPanel.setLayout(new GridLayout(3,2));
        logPanel.setBackground(Color.RED);

        //Jlabels 
        JLabel title = new JLabel("Places In Loo");
        title.setFont(new Font("Times", Font.BOLD, 45));
        title.setHorizontalAlignment(JLabel.CENTER);
        title_pan.add(title, BorderLayout.NORTH);

        
        JLabel user = new JLabel("Username");
        user.setFont(new Font("Times",Font.BOLD,14));
        logPanel.add(user);

        JTextField userText = new JTextField(20);
        userText.setSize(10,20);
        logPanel.add(userText);
        

        
        

        // jButtons

        // JTextfields
        
        
        
        
        
        login.add(title_pan, BorderLayout.NORTH);
        login.add(logPanel, BorderLayout.CENTER);
        login.setVisible(true);



        return login;

    }
}

