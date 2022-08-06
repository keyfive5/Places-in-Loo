import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class sublet_page extends JFrame implements ActionListener{
    private JFrame frame = new JFrame();
   
    private JLabel title = new JLabel("For Rent");
    //private String rentals [] = {"id:1, 24 Hickory Street", "id:2, 35 Lester", "id:3, 224 University Avenue","id:4, 232 Weber Street"};
    private ArrayList<String> rentals;
    private ArrayList<ArrayList<String>> rental_info;
    private JList list;
    private JLabel post_title = new JLabel(), post_desc = new JLabel(), post_start = new JLabel(), 
        post_end = new JLabel(), post_price = new JLabel(), post_sublessor = new JLabel(), post_contact = new JLabel();
    private JScrollPane scroll;
    private JButton get_post = new JButton("Get Post");
    private JButton rent = new JButton("Rent");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton btnHome = new JButton(home);
   
    
    // Intialize
    sublet_page() {
        /*rentals.add("id:1, 24 Hickory Street");
        rentals.add("id:2, 35 Lester");
        rentals.add("id:3, 224 University Avenue");
        rentals.add("id:4, 232 Weber Street");*/
        getListings();
        list = new JList<>(rentals.toArray());
        scroll = new JScrollPane(list);

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
        frame.add(btnHome);

        title.setFont(new Font("Calibri", Font.BOLD, 45));
        title.setBounds(120,10,1000,60);
        title.setFocusable(false);
        // Labels and Text Fields
        list.setBounds(50, 75, 300, 50);
        scroll.setBounds(50, 75, 300, 50);
        get_post.setBounds(50, 310, 100, 20);
        get_post.addActionListener(this);
        post_title.setBounds(200, 200, 100, 100);
       
        frame.add(title);
        frame.add(scroll);
        frame.add(get_post);
        frame.add (post_title);

       
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == get_post){
            //frame.remove(info);
            //System.out.println(list.getSelectedValue());

            int post_location = list.getSelectedIndex();
            post_title.setText(rentals.get(post_location));
            post_title.setBounds(50, 100, 1000, 100);
            post_title.setFont(new Font("Verdana", Font.PLAIN, 20));;
            post_title.setHorizontalAlignment(JLabel.LEADING);
            frame.add(post_title);

            post_start.setText("Start: " + rental_info.get(post_location).get(5));
            post_start.setBounds(50, 120, 1000, 100);
            post_start.setFont(new Font(null,Font.PLAIN,12));
            frame.add(post_start);

            post_end.setText("End: " + rental_info.get(post_location).get(6));
            post_end.setBounds(200, 120, 1000, 100);
            post_end.setFont(new Font(null,Font.PLAIN,12));
            frame.add(post_end);

            post_price.setText("Price per month: " + rental_info.get(post_location).get(7));
            post_price.setBounds(50, 137, 1000, 100);
            post_price.setFont(new Font(null,Font.PLAIN,15));
            frame.add(post_price);

            post_desc.setText("<html><b>Description: </b><br>" + rental_info.get(post_location).get(9)+"</htm>");
            post_desc.setBounds(50, 175, 1000, 100);
            post_desc.setFont(new Font(null,Font.PLAIN,15));
            frame.add(post_desc);

            post_sublessor.setText("Sublessor: " + rental_info.get(post_location).get(3) + ", " + rental_info.get(post_location).get(2));
            post_sublessor.setBounds(50, 220, 1000, 100);
            post_sublessor.setFont(new Font(null,Font.PLAIN,15));
            frame.add(post_sublessor);

            post_contact.setText("Contact: " + rental_info.get(post_location).get(4));
            post_contact.setBounds(50, 240, 1000, 100);
            post_contact.setFont(new Font(null,Font.PLAIN,15));
            frame.add(post_contact);

            rent.setBounds(200, 310, 100, 20);
            rent.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DatabaseConnection connection = new DatabaseConnection();
                    Boolean update = connection.updateQuery("UPDATE POSTS SET available=false WHERE post_id="+rental_info.get(post_location).get(0)+";");

                    if(update){
                        JOptionPane.showMessageDialog(frame, "Sublet is now pending.\nPlease contact the sublessor to confirm the sublet");
                    } else{
                        JOptionPane.showMessageDialog(frame, "An Error has occured!\nPlease try again or contact support.");
                    }
                }
            });
            frame.add(rent);
        }
  
       
    }

    private void getListings(){
        DatabaseConnection connection = new DatabaseConnection();
        rental_info = connection.retrieveQuery("SELECT * FROM POSTS WHERE available=true;");

        rentals = new ArrayList<String>();

        for (int i=0; i < rental_info.size();i++){
            rentals.add(rental_info.get(i).get(8));
        }
    }

    public static void main(String[] args) {
        sublet_page Menu = new sublet_page();
    }

}
