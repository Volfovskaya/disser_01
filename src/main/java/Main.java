import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        ArrayList<Employee> arrayListEmployee = new ArrayList<>();



        DBWorker dbWorker = new DBWorker();

        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_start");


            arrayListEmployee.add(new Employee());
            arrayListEmployee.add(new Employee());
            int i = 0;

            while (resultSet.next()) {
                int id = resultSet.getInt("employee_id");
                System.out.println(id);

                String str = resultSet.getString("employee_name");
                System.out.println(str);

                Date date = resultSet.getDate("employee_birth");
                System.out.println(date);


            }





            dbWorker.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
