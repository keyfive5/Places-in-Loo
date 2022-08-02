import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NewPost {
    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("Post a Sublet");
    private JLabel location = new JLabel("Location");
    private JLabel sublessor = new JLabel ("Sublessor");
    private JLabel duration = new JLabel("Duration");
    private JLabel description = new JLabel ("Description");
    private JTextField location_txt = new JTextField ("Enter Location");
    private JTextField sublessor_txt = new JTextField("Enter Sublessor");
    private JTextField duration_txt = new JTextField ("Enter Duration in months");
    private JTextField description_txt = new JTextField("Enter Description");
    private JButton submit_btn = new JButton ("Submit");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton home_btn = new JButton(home);
 
    NewPost(){
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

        location.setBounds(50,50,100,50);
        location.setFont(new Font(null,Font.PLAIN,15));
        location_txt.setBounds(120,65,200,30);

        sublessor.setBounds(50,90,100,50);
        sublessor.setFont(new Font(null,Font.PLAIN,15));
        sublessor_txt.setBounds(120,105,200,30);

        duration.setBounds(50,130,100,50);
        duration.setFont(new Font(null,Font.PLAIN,15));
        duration_txt.setBounds(120,145,200,30);

        description.setBounds(30,170,100,50);
        description.setFont(new Font(null,Font.PLAIN,15));
        description_txt.setBounds(120,185,200,150);

        submit_btn.setBounds(230,340,100,40);
        submit_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(new String("Enter Location").equals(location_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Location!");
                }
                if(new String("Enter Sublessor").equals(sublessor_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a vaild Sublessor!");
                }
                if(! isNumeric(duration_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a valid duration!");
                }
                if(new String("Enter Description").equals(description_txt.getText())){
                    JOptionPane.showMessageDialog(frame, "Enter a valid description!");
                }

                DatabaseConnection connection = new DatabaseConnection();
                String query = String.format("INSERT INTO USER VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);",
                null,null,null,null,null,new String("0/0/0000"),new String("0/0/0000"),
                0,location_txt.getText(),description_txt.getText(),true);
                connection.updateQuery(query);
                                 
            }
        });


            
        frame.add(title);
        frame.add(location);
        frame.add(sublessor);
        frame.add(duration);
        frame.add(description);
        frame.add(location_txt);
        frame.add(sublessor_txt);
        frame.add(duration_txt);
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
