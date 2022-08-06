import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;



public class LoginGUI extends JFrame {
    private JTextField tfusername;
    private JPasswordField tfpassword;
    private JLabel places;

    // Initializing the LoginGUI
    public void init() {

        // labels and text fields
        JLabel username_label = new JLabel("Username");
        tfusername = new JTextField();
        JLabel password_label = new JLabel("Password");
        tfpassword = new JPasswordField();

        // Panel 2 is for the buttons and text fields
        JPanel panel_contents = new JPanel();
        panel_contents.setLayout(new GridLayout(4, 1, 5, 5));
        panel_contents.setBackground(new Color(173, 216, 230));
        panel_contents.add(username_label);
        panel_contents.add(tfusername);
        panel_contents.add(password_label);
        panel_contents.add(tfpassword);

        // Places in 'Loo Label
        places = new JLabel("Places-in-Loo Login Page");
        places.setFont(new Font("Calibri", Font.BOLD, 45));
        places.setHorizontalAlignment(JLabel.CENTER);


        // Buttons
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {

            // This is what happens when Login is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String user = tfusername.getText();
                String pass = tfpassword.getText();    

                DatabaseConnection connection = new DatabaseConnection();
                ArrayList<ArrayList<String>> user_info = connection.retrieveQuery(String.format("SELECT * FROM USER WHERE" +
                                                        " username = '%s' AND password = '%s'", user,pass));
                // Check if user exists in database
                if (user_info != null && user_info.size() != 0){
                    User curr_user = new User(user_info);
                    setVisible(false);
                    MainMenu Menu = new MainMenu(); 
                } else {
                    JOptionPane.showMessageDialog(panel_contents, "Login credentials incorrect. Please try again.");
                }
            }
        });

        JButton btnCA = new JButton("Create Account");
        btnCA.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // Move to Create Account Page
                CreateAccount ca = new CreateAccount();
                setVisible(false);
            }
        });

        // Panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(7, 7, 5, 5));
        btnPanel.add(btnLogin);
        btnPanel.add(btnCA);
        btnPanel.setBackground(new Color(173, 216, 230));

        // Main panel
        JPanel panel_background = new JPanel();
        panel_background.setLayout(new BorderLayout());
        panel_background.setBackground(new Color(173, 216, 230));
        panel_background.add(panel_contents, BorderLayout.CENTER);
        panel_background.add(places, BorderLayout.PAGE_START);
        panel_background.add(btnPanel, BorderLayout.SOUTH);
       

        add(panel_background);

        // Size and Visibility
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Places in 'Loo");
        setMinimumSize(new Dimension(500, 500));
        setVisible(true);
    }

    public static void main(String[] args) {
        LoginGUI first = new LoginGUI();
        first.init();

    }

    public static void call_login_gui(){
        LoginGUI login = new LoginGUI();
        login.init();

    }
}