import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Rate {
    private float rating = 0;
    private ArrayList<ArrayList<String>> user_info;
    private ArrayList<String> users;
    private User curr_user;
    
    public Rate(User current_user){
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
        JList list;
        JScrollPane scroll;

        // Populate list with users to rate
        getUsers();
        list = new JList<>(users.toArray());
        scroll = new JScrollPane(list);
        list.setBounds(50, 25, 300, 50);
        scroll.setBounds(50, 25, 300, 50);

        frame.add(scroll);

        // Fill in who is current user
        curr_user = current_user;

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));

        btnHome.setBounds(5,5,32,32);
        btnHome.setBorder(null);
        btnHome.setBackground(null);
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenu Menu = new MainMenu(curr_user);
            }
        });
        panel.add(btnHome);
        
        lblQuest.setBounds(50,80,500,25);
        lblQuest.setFont(new Font(null,Font.PLAIN,15));
        panel.add(lblQuest);

        btnRate1.setBounds(80,110,32,32);
        btnRate1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 1;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate1);

        btnRate2.setBounds(130,110,32,32);
        btnRate2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 2;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate2);

        btnRate3.setBounds(180,110,32,32);
        btnRate3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 3;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate3);

        btnRate4.setBounds(230,110,32,32);
        btnRate4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 4;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate4);

        btnRate5.setBounds(280,110,32,32);
        btnRate5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 5;
                lblCurrent.setText("Current rating: " + rating + "/5");
            }
        });
        panel.add(btnRate5);

        lblCurrent.setBounds(160,143,500,25);
        lblCurrent.setFont(new Font(null,Font.PLAIN,11));
        panel.add(lblCurrent);

        txtReview.setBounds(20,175,360,120);
        panel.add(txtReview);

        btnSubmit.setBounds(20,320,100,40);
        btnSubmit.addActionListener(new ActionListener() {
            // Adds a review to the user and updates the database
            @Override
            public void actionPerformed(ActionEvent e) {
                int user_location = list.getSelectedIndex();
                DatabaseConnection conn;
                ArrayList<ArrayList<String>> result;
                float avgRating;
                float oldRating;
                int total_ratings = 0;
                if(rating == 0){
                    JOptionPane.showMessageDialog(frame, "Select a rating from 1 - 5");
                }
                conn = new DatabaseConnection();
                avgRating = rating;

                result = conn.retrieveQuery("SELECT rating,total_ratings FROM USER WHERE user_id=" + user_info.get(user_location).get(0));
                oldRating = Integer.parseInt(result.get(0).get(0));
                total_ratings = Integer.parseInt(result.get(0).get(1));
                // Update rating to new average rating if there was previous ratings
                if (oldRating != -1){
                    rating = ((oldRating * total_ratings) + rating) / (total_ratings + 1);
                }
                total_ratings += 1;

                // Update query to change rating in database
                conn.updateQuery("UPDATE USER SET rating = " + rating + ", total_ratings = " + total_ratings +
                     " WHERE user_id=" + user_info.get(user_location).get(0));

                JOptionPane.showMessageDialog(frame, "Rating successful!");

                // Return to Main Menu
                frame.dispose();
                MainMenu Menu = new MainMenu(curr_user);

            }
        });
        panel.add(btnSubmit);

        frame.add(panel,BorderLayout.CENTER);
        frame.setSize(420,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rating");
        frame.setVisible(true);
    }

    private void getUsers(){
        DatabaseConnection connection = new DatabaseConnection();
        user_info = connection.retrieveQuery("SELECT DISTINCT user_id, first_name, last_name FROM POSTS WHERE NOT user_id = " +
                             curr_user.getUserId() + ";");

        users = new ArrayList<String>();

        for (int i=0; i < user_info.size();i++){
            users.add(user_info.get(i).get(2) + ", " + user_info.get(i).get(1));
        }
    }

    public static void main(String[] args) {
        new Rate(new User());
    }
}
