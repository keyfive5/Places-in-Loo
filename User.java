public class User {
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
