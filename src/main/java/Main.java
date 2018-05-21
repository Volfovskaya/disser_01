import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Employee> arrayListEmployee = new ArrayList<>();



        DBWorker dbWorker = new DBWorker();

        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetEmployee = statement.executeQuery("SELECT * FROM employee_start");
            statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetCourse = statement.executeQuery("SELECT * FROM course");



            arrayListEmployee.add(new Employee());
            arrayListEmployee.add(new Employee());


            while (resultSetEmployee.next()) {
                int id = resultSetEmployee.getInt("employee_id");
                System.out.println(id);

                String str = resultSetEmployee.getString("employee_name");
                System.out.println(str);

                Date date = resultSetEmployee.getDate("employee_birth");
                System.out.println(date);


            }






        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
