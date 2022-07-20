import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

// Connector class that runs SQL queries through a database
public class DatabaseConnection {

    private String database;

    public DatabaseConnection(String database){
        this.database = database;
    }

    public ResultSet retrieveQuery(String query) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\" + this.database;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String name = rs.getString("username");
            System.out.println(name);

            if (conn != null) {
                conn.close();
            }

            return rs;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Return null if no result set
        return null;
    }

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
}
