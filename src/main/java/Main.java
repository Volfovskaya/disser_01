import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        DBWorker dbWorker = new DBWorker();

        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM employee_start");

            while (res.next()) {
                int id = res.getInt("employee_id");
                System.out.println(id);
                String str = res.getString("employee_name");
                System.out.println(str);
            }



            dbWorker.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
