import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

// Connector class that runs SQL queries through a database
public class DatabaseConnection {

    // Set this parameter to file location of of the database
    private String database;

    public DatabaseConnection(String database){
        this.database = database;
    }

    // Returns a 2D arraylist of strings of all records
    public ArrayList<ArrayList<String>> retrieveQuery(String query) {
        try {
            Connection conn = create_connection();  

            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();

            ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
            while (rs.next()){
                ArrayList<String> record = new ArrayList<String>();
                for (int i=0;i<columns;i++){
                    String value = rs.getString(i+1);
                    record.add(value);
                }
                result.add(record);
            }

            close_connection(conn);

            return result;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Return null if no result set
        return null;
    }


    // Enter the update query, returns True upon success
    public Boolean updateQuery(String query){
        try {
            Connection conn = create_connection();            

            Statement statement = conn.createStatement();

            statement.executeUpdate(query);

            close_connection(conn);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Connection create_connection(){
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:" + this.database;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void close_connection(Connection conn){
        try{
            if (conn != null) {
                conn.close();
            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
