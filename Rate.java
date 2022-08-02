import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rate {
    private int rating = 0;
    
    Rate(){
        Icon star = new ImageIcon("./Assets/star.png");
        Icon home = new ImageIcon("./Assets/home.png");
        JButton btnHome = new JButton(home);
        JButton btnRate1 = new JButton(star);
        JButton btnRate2 = new JButton(star);
        JButton btnRate3 = new JButton(star);
        JButton btnRate4 = new JButton(star);
        JButton btnRate5 = new JButton(star);
        JLabel lblQuest =new JLabel("How would you like to rate your pervious sublet?");
        JLabel lblCurrent = new JLabel("Current rating: " + rating + "/5");
        JButton btnSubmit = new JButton("Submit");
        JTextField txtReview = new JTextField("(optional) Enter a review");

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        //panel.setLayout(new GridLayout(4,1));
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));

        btnHome.setBounds(5,5,32,32);
        btnHome.setBorder(null);
        btnHome.setBackground(null);
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenu Menu = new MainMenu();
            }
        });
        panel.add(btnHome);
        
        lblQuest.setBounds(50,30,500,25);
        lblQuest.setFont(new Font(null,Font.PLAIN,15));
        panel.add(lblQuest);

        btnRate1.setBounds(80,60,32,32);
        btnRate1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 1;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate1);

        btnRate2.setBounds(130,60,32,32);
        btnRate2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 2;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate2);

        btnRate3.setBounds(180,60,32,32);
        btnRate3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 3;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate3);

        btnRate4.setBounds(230,60,32,32);
        btnRate4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 4;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate4);

        btnRate5.setBounds(280,60,32,32);
        btnRate5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 5;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate5);

        lblCurrent.setBounds(160,93,500,25);
        lblCurrent.setFont(new Font(null,Font.PLAIN,11));
        panel.add(lblCurrent);

        txtReview.setBounds(20,125,360,180);
        panel.add(txtReview);

        btnSubmit.setBounds(20,320,100,40);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(rating == 0){
                    JOptionPane.showMessageDialog(frame, "Select a rating from 1 - 5");
                }
                DatabaseConnection conn = new DatabaseConnection();
                float avgRating;
                avgRating = rating;

                
                if (new String("(optional) Enter a review").equals(txtReview.getText())){
                    return;
                } else {
                    System.out.printf(txtReview.getText());
                }
            }
        });
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
