import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rate {
    Rate(){

        Icon star = new ImageIcon("./assets/star.png");
        JButton btnRate1 = new JButton(star);
        JButton btnRate2 = new JButton(star);
        JButton btnRate3 = new JButton(star);
        JButton btnRate4 = new JButton(star);
        JButton btnRate5 = new JButton(star);
        JLabel lblQuest =new JLabel("How would you like to rate your pervious sublet?");
        JButton btnSubmit = new JButton("Submit");
        JTextField txtReview = new JTextField("(optional) Enter a review");

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //panel.setLayout(new GridLayout(4,1));
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        
        lblQuest.setBounds(50,30,500,25);
        lblQuest.setFont(new Font(null,Font.PLAIN,15));
        panel.add(lblQuest);

        btnRate1.setBounds(80,80,32,32);
        btnRate1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1 star");
            }
        });
        panel.add(btnRate1);

        btnRate2.setBounds(130,80,32,32);
        btnRate2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2 star");
            }
        });
        panel.add(btnRate2);

        btnRate3.setBounds(180,80,32,32);
        btnRate3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("3 star");
            }
        });
        panel.add(btnRate3);

        btnRate4.setBounds(230,80,32,32);
        btnRate4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("4 star");
            }
        });
        panel.add(btnRate4);

        btnRate5.setBounds(280,80,32,32);
        btnRate5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("5 star");
            }
        });
        panel.add(btnRate5);

        txtReview.setBounds(20,125,360,180);
        panel.add(txtReview);

        btnSubmit.setBounds(20,320,100,40);
        panel.add(btnSubmit);

        frame.add(panel,BorderLayout.CENTER);
        frame.setSize(420,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rating");
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Rate();
    }
}
