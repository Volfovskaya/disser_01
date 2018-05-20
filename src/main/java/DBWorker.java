import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private final static String URL = "jdbc:mysql://localhost:3306/databasediploma_01";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "02241995";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }


    public DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
