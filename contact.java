import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class contact extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel title = new JLabel("Message");
    JButton send = new JButton("Send");
    JButton back = new JButton("Back");
    JTextField send_to = new JTextField("TO:");
    JTextArea message = new JTextArea("Message:");

    J
    


    // Intialize
    contact() {
        title.setFont(new Font("Calibri", Font.BOLD, 45));
        title.setBounds(120,10,1000,60);
        title.setFocusable(false);
        // Labels and Text Fields

        send_to.setBounds(20,65,350,30);

        message.setBounds(20, 100, 350, 200);

        send.setBounds(150,320,100,40);
        back.setBounds(275, 320, 100, 40);
       
        frame.add(title);
        frame.add(send_to);
        frame.add(message);
        frame.add(send);
        frame.add(back);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
  
    }

    public static void main(String[] args) {
        contact Menu = new contact();
    }

}
