import java.util.ArrayList;

public class User {
    // Private variables
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private String campus;
    private String date_of_birth;
    private float rating;
    private int total_ratings;

    // Default constructor for testing
    public User(){
        this.user_id = 0;
        this.username = "";
        this.password = "";
        this.email = "";
        this.first_name = "";
        this.last_name = "";
        this.gender = "";
        this.campus = "";
        this.date_of_birth = "";
        this.rating = -1;
        this.total_ratings = 0;
    }

    // Constructor for all fields
    public User(int user_id,String username, String password, String email, String first_name, String last_name, 
        String gender, String campus, String date_of_birth, float rating, int total_ratings){
            this.user_id = user_id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.gender = gender;
            this.campus = campus;
            this.date_of_birth = date_of_birth;
            this.rating = rating;
            this.total_ratings = total_ratings;
    }

    // Overloaded constructor to get variables from database input
    public User(ArrayList<ArrayList<String>> user){
        if (user.size() > 0 && user.get(0).size() == 11){
            this.user_id = Integer.parseInt(user.get(0).get(0));
            this.username = user.get(0).get(1);
            this.password = user.get(0).get(2);
            this.email = user.get(0).get(3);
            this.first_name = user.get(0).get(4);
            this.last_name = user.get(0).get(5);
            this.gender = user.get(0).get(6);
            this.campus = user.get(0).get(7);
            this.date_of_birth = user.get(0).get(8);
            this.rating = Float.parseFloat(user.get(0).get(9));
            this.total_ratings = Integer.parseInt(user.get(0).get(10));
        }
    }

    // Get methods for all private variables
    public int getUserId(){
        return this.user_id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public String getGender(){
        return this.gender;
    }

    public String getCampus(){
        return this.campus;
    }

    public String getDateOfBirth(){
        return this.date_of_birth;
    }

    public float getRating(){
        return this.rating;
    }

    public int getTotalRatings(){
        return this.total_ratings;
    }
}
