public class ConnectionExample {
    public static void main(String[] args){
        // Object used to connect to the database
         DatabaseConnection conn = new DatabaseConnection("Places-in-Loo\\data.db");
        
        // Insert values into table example
        //conn.updateQuery("INSERT INTO USER VALUES(null,'username','password','email@mylaurier.ca','firstname','lastname','gender','university','2001-11-02');");
        
        // Select query example to retrieve data
        String[] result = conn.retrieveQuery("SELECT * FROM USER WHERE username='shai1640';");
        
        // Retrieve query returns list of strings, can find all field values in these strings
        for (int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}
