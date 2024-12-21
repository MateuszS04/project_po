package data_base;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String URL="jdbc:sqlite:login_data_base.sqlite";
        try{
            Class.forName("org.sqlite.JDBC");
            databaseLink = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception");
        }
        return databaseLink;
    }
    public void closeConnection() {
        if (databaseLink != null) {
            try{
                databaseLink.close();
            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to close connection");
            }
        }
    }
}
