import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class NewPost {
    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("Post a Sublet");
    private JLabel location = new JLabel("Location");
    private JLabel startDate = new JLabel ("Start date");
    private JLabel endDate = new JLabel ("End date");
    private JLabel price = new JLabel("Price per month");
    private JLabel description = new JLabel ("Description");
    private JTextField location_txt = new JTextField ("Enter Location");
    private JFormattedTextField startDate_txt = new JFormattedTextField(new SimpleDateFormat("yyyy-mm-dd"));
    private JFormattedTextField endDate_txt = new JFormattedTextField(new SimpleDateFormat("yyyy-mm-dd"));
    private JTextField price_txt = new JTextField ("Enter the Price per month");
    private JTextField description_txt = new JTextField("Enter Description");
    private JButton submit_btn = new JButton ("Submit");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton home_btn = new JButton(home);
 
    public NewPost(User curr_user){
        home_btn.setBounds(5,5,32,32);
        home_btn.setBorder(null);
        home_btn.setBackground(null);
        home_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MainMenu Menu = new MainMenu(curr_user);
            }
        });
        frame.add(home_btn);

        title.setBounds(125,10,1000,50);
        title.setFont(new Font(null,Font.PLAIN,25));

        location.setBounds(56,55,100,50);
        location.setFont(new Font(null,Font.PLAIN,15));
        location_txt.setBounds(120,65,210,30);

        startDate.setBounds(50,90,100,50);
        startDate.setFont(new Font(null,Font.PLAIN,15));
        startDate_txt.setBounds(120,100,70,30);
        startDate_txt.setText("yyyy-mm-dd");

        endDate.setBounds(195,90,100,50);
        endDate.setFont(new Font(null,Font.PLAIN,15));
        endDate_txt.setBounds(260,100,70,30);
        endDate_txt.setText("yyyy-mm-dd");

        price.setBounds(10,125,150,50);
        price.setFont(new Font(null,Font.PLAIN,15));
        price_txt.setBounds(120,135,210,30);

        description.setBounds(30,155,100,50);
        description.setFont(new Font(null,Font.PLAIN,15));
        description_txt.setBounds(120,170,210,100);

        submit_btn.setBounds(230,280,100,40);
        submit_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String("Enter Location").equals(location_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Location!");
                    return;
                }
                if(!isNumeric(price_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Price!");
                    return;
                }
                if(new String("Enter Description").equals(description_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a valid description!");
                    return;
                }

                DatabaseConnection connection = new DatabaseConnection();

                String query = String.format("INSERT INTO POSTS VALUES(%s,%s,'%s','%s','%s','%s','%s',%s,'%s','%s',%s);",
                null,curr_user.getUserId(),curr_user.getFirstName(),curr_user.getLastName(),
                curr_user.getEmail(),startDate_txt.getText(),endDate_txt.getText(),price_txt.getText(),location_txt.getText(),
                description_txt.getText(),true);
                System.out.println(query);

                connection.updateQuery(query);
                JOptionPane.showMessageDialog(frame, "Sublet successfully posted.");
            }
        });


            
        frame.add(title);
        frame.add(location);
        frame.add(startDate);
        frame.add(endDate);
        frame.add(price);
        frame.add(description);
        frame.add(location_txt);
        frame.add(startDate_txt);
        frame.add(endDate_txt);
        frame.add(price_txt);
        frame.add(description_txt);
        frame.add(submit_btn);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    
    }
    public static void main(String[] args) {
        NewPost post = new NewPost(new User());
    }

    private static boolean isNumeric(String string) {
        int intValue;
               
        if(string == null || string.equals("")) {
            return false;
        }
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
