package data_base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class users {
    public static final String Driver = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:sqlite:baza_danych_kont.sqlite";

    private Connection conn;
    private Statement stmt;

    public users() {
        try {
            conn = DriverManager.getConnection(URL);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem with connecting to database");
            e.printStackTrace();
        }
    }


}
