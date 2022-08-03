import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Cancel {
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("Are sure you want to cancel your current sublet?");
    private JButton cancel_btn = new JButton("Cancel");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton home_btn = new JButton(home);
    
    Cancel(){
        home_btn.setBounds(5,5,32,32);
        home_btn.setBorder(null);
        home_btn.setBackground(null);
        home_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenu Menu = new MainMenu();
            }
        });
        frame.add(home_btn);    
    
        label.setBounds(20,30,400,100);
        label.setFont(new Font(null,Font.PLAIN,25));

        cancel_btn.setBounds(100,80,200,60);
        cancel_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your sublet has been canceled.");
            }
        });
        
        frame.add(label);
        frame.add(cancel_btn);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    return;
    }
}
