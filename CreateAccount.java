
import java.awt.*;

import javax.swing.*;

public class CreateAccount extends JFrame {
    JTextField username, password, email, first_name, last_name, gender, campus, date_of_birth;
    JLabel ca;

    // Intialize
    public void init() {
        // Labels and Text Fields
        JLabel username_label = new JLabel("Username");
        username = new JTextField();

        JLabel password_label = new JLabel("Password");
        password = new JTextField();

        JLabel email_label = new JLabel("Email");
        email = new JTextField();

        JLabel first_name_label = new JLabel("First Name");
        first_name = new JTextField();

        JLabel last_name_label = new JLabel("Last Name");
        first_name = new JTextField();

        JLabel gender_label = new JLabel("Gender");
        gender = new JTextField();

        JLabel campus_label = new JLabel("Campus");
        campus = new JTextField();

        JLabel date_of_birth_label = new JLabel("Date of Birth");
        date_of_birth = new JTextField();

        // Panel with labels and text fields
        JPanel panel_two = new JPanel();
        panel_two.add(username_label);
        panel_two.add(username);
        panel_two.add(password_label);
        panel_two.add(password);
        panel_two.add(email_label);
        panel_two.add(email);
        panel_two.add(first_name_label);
        panel_two.add(first_name);
        panel_two.add(last_name_label);
        panel_two.add(last_name);
        panel_two.add(gender_label);
        panel_two.add(gender);
        panel_two.add(campus_label);
        panel_two.add(campus);
        panel_two.add(date_of_birth_label);
        panel_two.add(date_of_birth);

        // Places in 'Loo Label
        ca = new JLabel("Create Account");
        ca.setFont(new Font("Calibri", Font.BOLD, 45));

        // Main Panel
        JPanel panel_one = new JPanel();
        panel_one.setLayout(new BorderLayout());
        panel_one.setBackground(new Color(173, 216, 230));
        panel_one.add(panel_two, BorderLayout.CENTER);
        panel_one.add(ca, BorderLayout.PAGE_START);

        add(panel_one);

        // Size and Visibility
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Places in 'Loo - Create Account");
        setMinimumSize(new Dimension(500, 500));
        setVisible(true);

    }

    public static void main(String[] args) {
        CreateAccount ca = new CreateAccount();
        ca.init();
    }
}