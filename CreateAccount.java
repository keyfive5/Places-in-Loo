
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.*;
import javax.swing.*;


public class CreateAccount extends JFrame {
    private JTextField username, password, email, first_name, last_name, gender, campus, date_of_birth;
    private JLabel title;
    private JButton submit_btn, back_btn; 

    // Intialize
    public CreateAccount () {
        // Title Label
        title = new JLabel("Create Account");
        title.setBounds(100,10,1000,60);
        title.setFont(new Font("Calibri", Font.BOLD, 45));

        // Labels and Text Fields
        JLabel username_label = new JLabel("Username");
        username_label.setBounds(50,60,200,50);
        username_label.setFont(new Font("Calibri", NORMAL, 24));
        username = new JTextField("Enter username here");
        username.setBounds(220,70,220,40);

        JLabel password_label = new JLabel("Password");
        password_label.setBounds(50,100,200,60);
        password_label.setFont(new Font("Calibri", NORMAL, 24));
        password = new JTextField("Enter password here");
        password.setBounds(220,110,220,40);

        JLabel email_label = new JLabel("Email");
        email_label.setBounds(50,140,200,60);
        email_label.setFont(new Font("Calibri", NORMAL, 24));
        email = new JTextField("Enter email here");
        email.setBounds(220,150,220,40);

        JLabel first_name_label = new JLabel("First Name");
        first_name_label.setBounds(50,180,200,60);
        first_name_label.setFont(new Font("Calibri", NORMAL, 24));
        first_name = new JTextField("Enter first name here");
        first_name.setBounds(220,190,220,40);

        JLabel last_name_label = new JLabel("Last Name");
        last_name_label.setBounds(50,220,200,60);
        last_name_label.setFont(new Font("Calibri", NORMAL, 24));
        last_name = new JTextField("Enter last name here");
        last_name.setBounds(220,230,220,40);

        JLabel gender_label = new JLabel("Gender");
        gender_label.setBounds(50,260,200,60);
        gender_label.setFont(new Font("Calibri", NORMAL, 24));
        gender = new JTextField("Enter gender here");
        gender.setBounds(220,270,220,40);

        JLabel campus_label = new JLabel("Campus");
        campus_label.setBounds(50,300,200,60);
        campus_label.setFont(new Font("Calibri", NORMAL, 24));
        campus = new JTextField("Enter campus here");
        campus.setBounds(220,310,220,40);

        JLabel date_of_birth_label = new JLabel("Date of Birth");
        date_of_birth_label.setBounds(50,340,200,60);
        date_of_birth_label.setFont(new Font("Calibri", NORMAL, 24));
        date_of_birth = new JTextField("Enter date of birth here");
        date_of_birth.setBounds(220,350,220,40);

        // Add all labels and fields to frame
        JFrame frame = new JFrame();
        frame.setBackground(new Color(173, 216, 230));
        frame.add(username_label);
        frame.add(username);
        frame.add(password_label);
        frame.add(password);
        frame.add(email_label);
        frame.add(email);
        frame.add(first_name_label);
        frame.add(first_name);
        frame.add(last_name_label);
        frame.add(last_name);
        frame.add(gender_label);
        frame.add(gender);
        frame.add(campus_label);
        frame.add(campus);
        frame.add(date_of_birth_label);
        frame.add(date_of_birth);
        frame.add(title);        

        // Buttons
        submit_btn = new JButton("Submit");
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Email Validation
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                String strEmail = email.getText();
                Matcher matcher = pattern.matcher(strEmail);
                if (!matcher.matches()){
                    JOptionPane.showMessageDialog(frame, "Invalid email address");
                    return;
                }
                // Waterloo or Laurier email validation
                String domain = strEmail.substring(strEmail.indexOf("@")+1, strEmail.lastIndexOf("."));
                if (!domain.equals("mylaurier") && !domain.equals("uwaterloo")){
                    JOptionPane.showMessageDialog(frame, "Not a laurier (mylaurier domain) or" +
                     " waterloo (uwaterloo domain) email.");
                     return;
                }
                // Check if username is unique
                DatabaseConnection connection = new DatabaseConnection();
                ArrayList<ArrayList<String>> usernames = connection.retrieveQuery("SELECT username FROM USER");
                if (usernames != null){
                    for (int i=0;i<usernames.size();i++){
                        if (usernames.get(i).contains(username.getText())){
                            JOptionPane.showMessageDialog(frame, "Username already taken. Please try a different username");
                            return;
                        }
                    }
                }

                String query = String.format("INSERT INTO USER VALUES(%s,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                        null,username.getText(),password.getText(),email.getText(),first_name.getText(),last_name.getText(),
                        gender.getText(),campus.getText(),date_of_birth.getText(),-1,0);
                connection.updateQuery(query);

                JOptionPane.showMessageDialog(frame, "Account successfully created");

                // Go back to Login page
                LoginGUI.call_login_gui();
                setVisible(false);
            }
        });
        back_btn = new JButton("Back");
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to Login page
                LoginGUI.call_login_gui();
                setVisible(false);
            }
        });
        submit_btn.setBounds(220,400,220,40);
        back_btn.setBounds(10,400,220,40);
        frame.add(submit_btn);
        frame.add(back_btn);

        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Places in 'Loo - Create Account");
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);    

    }

    public static void main(String[] args) {
        CreateAccount ca = new CreateAccount();
    }
}