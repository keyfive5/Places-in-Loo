import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Cancel {
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("<html>Select a sublet<br>to cancel</html>",SwingConstants.CENTER);
    private JButton cancel_btn = new JButton("Cancel");
    private Icon home = new ImageIcon("./Assets/home.png");
    private JButton home_btn = new JButton(home);
    private JList list;
    private JScrollPane scroll;
    private User curr_user;
    private ArrayList<String> posts;
    private ArrayList<ArrayList<String>> posts_info;

    
    public Cancel(User current_user){

        // Set current user
        curr_user = current_user;

        // Home button to return to main menu
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
    
        // Get all your postings and populate it into the list
        getPostings();
        list = new JList<>(posts.toArray());
        scroll = new JScrollPane(list);
        list.setBounds(50, 25, 300, 50);
        scroll.setBounds(50, 25, 300, 50);

        label.setBounds(20,80,400,100);
        label.setFont(new Font(null,Font.PLAIN,25));

        cancel_btn.setBounds(100,180,200,60);
        cancel_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int post_location = list.getSelectedIndex();
                DatabaseConnection connection = new DatabaseConnection();
                connection.updateQuery("UPDATE POSTS SET available=false WHERE post_id="+posts_info.get(post_location).get(0)+";");
                JOptionPane.showMessageDialog(frame, "Your sublet has been canceled.");

                // Return to Main Menu
                frame.dispose();
                MainMenu Menu = new MainMenu(curr_user);
            }
        });
        
        frame.add(label);
        frame.add(cancel_btn);
        frame.add(scroll);
        frame.add(home_btn);  
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(173, 216, 230));
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

        return;
    }

    private void getPostings(){
        DatabaseConnection connection = new DatabaseConnection();
        posts_info = connection.retrieveQuery("SELECT post_id,location FROM POSTS WHERE available=true and user_id = " + curr_user.getUserId());

        posts = new ArrayList<String>();

        for (int i=0; i < posts_info.size();i++){
            posts.add(posts_info.get(i).get(1));
        }
    }

    public static void main(String[] args) {
        new Cancel(new User());
    }
}
