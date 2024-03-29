import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Rent extends JFrame implements ActionListener{
    private JFrame frame = new JFrame();
   
    private JLabel title = new JLabel("For Rent");
    private ArrayList<String> rentals;
    private ArrayList<ArrayList<String>> rental_info;
    private JList list;
    private JLabel post_title = new JLabel(), post_desc = new JLabel(), post_start = new JLabel(), 
        post_end = new JLabel(), post_price = new JLabel(), post_sublessor = new JLabel(), post_contact = new JLabel(), post_rating = new JLabel();
    private JScrollPane scroll;
    private JButton get_post = new JButton("Get Post");
    private JButton rent = new JButton("Rent");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton btnHome = new JButton(home);
    private User curr_user;
   
    
    // Intialize
    public Rent(User current_user) {
        // Set current user
        curr_user = current_user;

        // Get current listings that are not your own and populate the list
        getListings();
        list = new JList<>(rentals.toArray());
        scroll = new JScrollPane(list);

        // Home button to return to Main menu
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
        frame.add(btnHome);

        title.setFont(new Font("Calibri", Font.BOLD, 45));
        title.setBounds(120,10,1000,60);
        title.setFocusable(false);
        // Labels and Text Fields
        list.setBounds(50, 75, 300, 50);
        scroll.setBounds(50, 75, 300, 50);
        get_post.setBounds(50, 340, 100, 20);
        get_post.addActionListener(this);
        post_title.setBounds(200, 200, 100, 100);
       
        // Add all the objects to the frame
        frame.add(title);
        frame.add(scroll);
        frame.add(get_post);
        frame.add (post_title);
        
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(620,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        // Action when "Get Post" button pressed
        if(e.getSource() == get_post){
            // Error check to see if posting selected
            try{
                int post_location = list.getSelectedIndex();
                DatabaseConnection connection = new DatabaseConnection();
                ArrayList<ArrayList<String>> result;
                String rating;
    
                // Create labels to display information
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
                
                // Get posting users rating
                result = connection.retrieveQuery("SELECT rating FROM USER WHERE user_id=" + rental_info.get(post_location).get(1));
                rating = result.get(0).get(0);
                // If there is no rating, print "No ratings yet"
                if (rating.equals("-1")){
                    rating = "No ratings yet";
                }
    
                post_rating.setText("Rating: " + rating);
                post_rating.setBounds(50, 260, 1000, 100);
                post_rating.setFont(new Font(null,Font.PLAIN,15));
                frame.add(post_rating);
    
                rent.setBounds(200, 340, 100, 20);
                rent.addActionListener(new ActionListener() {
    
                    // Action when "Rent" button pressed
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DatabaseConnection connection = new DatabaseConnection();
                        Boolean update = connection.updateQuery("UPDATE POSTS SET available=false WHERE post_id="+rental_info.get(post_location).get(0)+";");
    
                        if(update){
                            JOptionPane.showMessageDialog(frame, "Sublet is now pending.\nPlease contact the sublessor to confirm the sublet");
                            // Return to Main Menu
                            frame.dispose();
                            MainMenu Menu = new MainMenu(curr_user);
                        } else{
                            JOptionPane.showMessageDialog(frame, "An Error has occured!\nPlease try again or contact support.");
                        }
                    }
                });
                frame.add(rent);
            } catch (ArrayIndexOutOfBoundsException exception){
                JOptionPane.showMessageDialog(frame, "You did not select a posting.");
            }
        }
  
       
    }

    private void getListings(){
        DatabaseConnection connection = new DatabaseConnection();
        rental_info = connection.retrieveQuery("SELECT * FROM POSTS WHERE available=true and NOT user_id = " + curr_user.getUserId());

        rentals = new ArrayList<String>();

        for (int i=0; i < rental_info.size();i++){
            rentals.add(rental_info.get(i).get(8));
        }
    }

    public static void main(String[] args) {
        Rent Menu = new Rent(new User());
    }

}
