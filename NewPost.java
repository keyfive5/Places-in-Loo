import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.ArrayList;

public class NewPost {
    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("Post a Sublet");
    private JLabel location = new JLabel("Location");
    private JLabel sublessor = new JLabel ("Sublessor");
    private JLabel startDate = new JLabel ("Start date");
    private JLabel endDate = new JLabel ("End date");
    private JLabel price = new JLabel("Price per month");
    private JLabel description = new JLabel ("Description");
    private JTextField location_txt = new JTextField ("Enter Location");
    private JTextField sublessor_txt = new JTextField("Enter Sublessor");
    private JFormattedTextField startDate_txt = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
    private JFormattedTextField endDate_txt = new JFormattedTextField(new SimpleDateFormat("dd/mm/yyyy"));
    private JTextField price_txt = new JTextField ("Enter the Price per month");
    private JTextField description_txt = new JTextField("Enter Description");
    private JButton submit_btn = new JButton ("Submit");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton home_btn = new JButton(home);
 
    public NewPost(){
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

        title.setBounds(125,10,1000,50);
        title.setFont(new Font(null,Font.PLAIN,25));

        location.setBounds(56,55,100,50);
        location.setFont(new Font(null,Font.PLAIN,15));
        location_txt.setBounds(120,65,210,30);

        sublessor.setBounds(47,93,100,50);
        sublessor.setFont(new Font(null,Font.PLAIN,15));
        sublessor_txt.setBounds(120,105,210,30);

        startDate.setBounds(50,135,100,50);
        startDate.setFont(new Font(null,Font.PLAIN,15));
        startDate_txt.setBounds(120,145,70,30);
        startDate_txt.setText("dd/mm/yyyy");

        endDate.setBounds(195,135,100,50);
        endDate.setFont(new Font(null,Font.PLAIN,15));
        endDate_txt.setBounds(260,145,70,30);
        endDate_txt.setText("dd/mm/yyyy");

        price.setBounds(10,170,150,50);
        price.setFont(new Font(null,Font.PLAIN,15));
        price_txt.setBounds(120,180,210,30);

        description.setBounds(30,220,100,50);
        description.setFont(new Font(null,Font.PLAIN,15));
        description_txt.setBounds(120,220,210,100);

        submit_btn.setBounds(230,340,100,40);
        submit_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String("Enter Location").equals(location_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Location!");
                    return;
                }
                if(new String("Enter Sublessor").equals(sublessor_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Sublessor!");
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

                String username = sublessor_txt.getText();
                DatabaseConnection connection = new DatabaseConnection();
                //ArrayList<ArrayList<String>>  = connection.retrieveQuery(new String("SELECT * FROM USER WHERE" +
                //                                      " username = '" + username +"';"));
                ArrayList<ArrayList<String>> user_info = connection.retrieveQuery("SELECT user_id, first_name, last_name, email FROM USER WHERE username='"+ username+"';");

                if (user_info != null && user_info.size() != 0){

                    String query = String.format("INSERT INTO USER VALUES(%s,%s,'%s','%s','%s',%s,%s,%s,'%s','%s',%s);",
                    null,user_info.get(0).get(0),user_info.get(0).get(1),user_info.get(0).get(2),
                    user_info.get(0).get(3),startDate_txt.getText(),endDate_txt.getText(),price_txt.getText(),location_txt.getText(),
                    description_txt.getText(),true);

                    connection.updateQuery(query);
                    JOptionPane.showMessageDialog(frame, "Sublet successfully posted.");

                } else {
                    JOptionPane.showMessageDialog(frame, "Invaild Subleassor\nPlease make sure username is same as Sublessor");
                    return;
                }
            }
        });


            
        frame.add(title);
        frame.add(location);
        frame.add(sublessor);
        frame.add(startDate);
        frame.add(endDate);
        frame.add(price);
        frame.add(description);
        frame.add(location_txt);
        frame.add(sublessor_txt);
        frame.add(startDate_txt);
        frame.add(endDate_txt);
        frame.add(price_txt);
        frame.add(description_txt);
        frame.add(submit_btn);

        
        //frame.add(label);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    
    }
    public static void main(String[] args) {
        NewPost post = new NewPost();
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
