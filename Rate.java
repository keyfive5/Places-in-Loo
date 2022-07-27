import java.awt.*;
import javax.swing.*;

public class Rate {
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("Test");
 
    Rate(){
    
        label.setBounds(0,0,100,50);
        label.setFont(new Font(null,Font.PLAIN,25));
        
        frame.add(label);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Rate Menu = new Rate();
    }
}
