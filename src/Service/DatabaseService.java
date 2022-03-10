package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {

    private static final String url = "jdbc:sqlite:phonebook.db";
    

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn =  DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        return conn;
    }

    public DatabaseService() throws ClassNotFoundException {
        
    }
    
}
