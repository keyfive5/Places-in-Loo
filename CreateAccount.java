
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;



public class CreateAccount extends JFrame {
    JTextField username, password, email, first_name, last_name, gender, campus, date_of_birth;
    JLabel ca;
    JButton submit_btn;

    // Intialize
    public void init() {
        // Labels and Text Fields
        JLabel username_label = new JLabel("Username");
        username_label.setHorizontalAlignment(JLabel.CENTER);
        username = new JTextField("Enter username here");

        JLabel password_label = new JLabel("Password");
        password_label.setHorizontalAlignment(JLabel.CENTER);
        password = new JTextField("Enter password here");

        JLabel email_label = new JLabel("Email");
        email_label.setHorizontalAlignment(JLabel.CENTER);
        email = new JTextField("Enter email here");

        JLabel first_name_label = new JLabel("First Name");
        first_name_label.setHorizontalAlignment(JLabel.CENTER);
        first_name = new JTextField("Enter first name here");

        JLabel last_name_label = new JLabel("Last Name");
        last_name_label.setHorizontalAlignment(JLabel.CENTER);
        last_name = new JTextField("Enter last name here");

        JLabel gender_label = new JLabel("Gender");
        gender_label.setHorizontalAlignment(JLabel.CENTER);
        gender = new JTextField("Enter gender here");

        JLabel campus_label = new JLabel("Campus");
        campus_label.setHorizontalAlignment(JLabel.CENTER);
        campus = new JTextField("Enter campus here");

        JLabel date_of_birth_label = new JLabel("Date of Birth");
        date_of_birth_label.setHorizontalAlignment(JLabel.CENTER);
        date_of_birth = new JTextField("Enter date of birth here");

        // Panel with labels and text fields
        JPanel panel_contents = new JPanel(new GridLayout(9,2,5,5));
        panel_contents.setBackground(new Color(173, 216, 230));
        panel_contents.add(username_label);
        panel_contents.add(username);
        panel_contents.add(password_label);
        panel_contents.add(password);
        panel_contents.add(email_label);
        panel_contents.add(email);
        panel_contents.add(first_name_label);
        panel_contents.add(first_name);
        panel_contents.add(last_name_label);
        panel_contents.add(last_name);
        panel_contents.add(gender_label);
        panel_contents.add(gender);
        panel_contents.add(campus_label);
        panel_contents.add(campus);
        panel_contents.add(date_of_birth_label);
        panel_contents.add(date_of_birth);

        // Places in 'Loo Label
        ca = new JLabel("Create Account");
        ca.setHorizontalAlignment(JLabel.CENTER);
        ca.setFont(new Font("Calibri", Font.BOLD, 45));

        // submit button 

        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validation
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                String strEmail = email.getText();
                Matcher matcher = pattern.matcher(strEmail);
                if (!matcher.matches()){
                    JOptionPane.showMessageDialog(panel_contents, "Invalid email address");
                    return;
                }
                String domain = strEmail.substring(strEmail.indexOf("@")+1, strEmail.lastIndexOf("."));
                if (!domain.equals("mylaurier") && !domain.equals("uwaterloo")){
                    JOptionPane.showMessageDialog(panel_contents, "Not a laurier (mylaurier domain) or waterloo (uwaterloo domain) email.");
                }
                
                CreateAccount.call_create_acct();
                setVisible(false);
            }
        });
        panel_contents.add(new JLabel(""));
        panel_contents.add(submit_btn);

        // Main Panel
        JPanel panel_background = new JPanel();
        panel_background.setLayout(new BorderLayout());
        panel_background.setBackground(new Color(173, 216, 230));
        panel_background.add(panel_contents, BorderLayout.CENTER);
        panel_background.add(ca, BorderLayout.PAGE_START);

        add(panel_background);

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

    public static void call_create_acct(){
        CreateAccount ca = new CreateAccount();
        ca.init();

    }
}