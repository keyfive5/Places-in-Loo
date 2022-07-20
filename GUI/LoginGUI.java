import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class LoginGUI extends JFrame {
    JTextField tfusername;
    JPasswordField tfpassword;
    JLabel places;

    // Initializing the LoginGUI
    public void init() {

        // labels and text fields
        JLabel username_label = new JLabel("Username");
        tfusername = new JTextField();
        JLabel password_label = new JLabel("Password");
        tfpassword = new JPasswordField();

        // Panel 2 is for the buttons and text fields
        JPanel panel_two = new JPanel();
        panel_two.setLayout(new GridLayout(4, 1, 5, 5));
        panel_two.setBackground(new Color(173, 216, 230));
        panel_two.add(username_label);
        panel_two.add(tfusername);
        panel_two.add(password_label);
        panel_two.add(tfpassword);

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
                // Finish Later
            }
        });

        JButton btnCA = new JButton("Create Account");
        btnCA.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // Move to Create Account Page
                CreateAccount.call_create_acct();
                System.out.println("page changed");
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
        JPanel panel_one = new JPanel();
        panel_one.setLayout(new BorderLayout());
        panel_one.setBackground(new Color(173, 216, 230));
        panel_one.add(panel_two, BorderLayout.CENTER);
        panel_one.add(places, BorderLayout.PAGE_START);
        panel_one.add(btnPanel, BorderLayout.SOUTH);
       

        add(panel_one);

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
}