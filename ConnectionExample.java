import java.util.ArrayList;

public class ConnectionExample {
    public static void main(String[] args){
        // Object used to connect to the database
         DatabaseConnection conn = new DatabaseConnection();        

        // Select query example to retrieve data
        ArrayList<ArrayList<String>> result = conn.retrieveQuery("SELECT * FROM USER WHERE username='shai1640';");
        
        // Retrieve query returns list of strings, can find all field values in these strings
        for (int i=0;i<result.get(0).size();i++){
            System.out.println(result.get(0).get(i));
        }
    }
}
