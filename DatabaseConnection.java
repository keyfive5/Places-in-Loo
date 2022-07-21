import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// Connector class that runs SQL queries through a database
public class DatabaseConnection {

    // Set this parameter to file location of of the database
    private String database;

    public DatabaseConnection(String database){
        this.database = database;
    }

    public String[] retrieveQuery(String query) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\" + this.database;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            String[] result = new String[columns];
            while (rs.next()){
                for (int i=0;i<columns;i++){
                    result[i] = rs.getString(i+1);
                }
            }
            
            //String name = rs.getString("username");

            if (conn != null) {
                conn.close();
            }

            return result;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Return null if no result set
        return null;
    }


    // Enter the update query, returns True upon success
    public Boolean updateQuery(String query){
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\" + this.database;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");

            Statement statement = conn.createStatement();

            statement.executeUpdate(query);

            if (conn != null) {
                conn.close();
            }  
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //public Boolean insert(String tablename, String values){
    //    String query = "INSERT INTO " + tablename + " VALUES(" + values + ")";
    //    return this.updateQuery(query);
    //}
//
    //public ResultSet retrieve(String fields, String tablename, String arguments){
    //    String query = "SELECT " + fields + " FROM "
    //    return ;
    //}
}
