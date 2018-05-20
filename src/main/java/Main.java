import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Main {
    private final static String URL = "jdbc:mysql://localhost:3306/databasediploma_01";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "02241995";


    public static void main(String[] args) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось загрузить класс драйвера!");
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            System.out.println("Соединение: " + connection.isClosed()); // check

            ResultSet res = statement.executeQuery("SELECT * FROM employee_start");

            while (res.next()) {
                int id = res.getInt(1);
                System.out.println(id);
                String str = res.getString(2);
                System.out.println(str);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
