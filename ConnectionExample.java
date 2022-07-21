public class ConnectionExample {
    public static void main(String[] args){
        DatabaseConnection conn = new DatabaseConnection("Places-in-Loo\\data.db");
        conn.updateQuery("INSERT INTO USER VALUES(null,'username','password','email@mylaurier.ca','firstname','lastname','gender','university','2001-11-02');");
        String[] result = conn.retrieveQuery("SELECT * FROM USER WHERE username='username';");
        for (int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}
