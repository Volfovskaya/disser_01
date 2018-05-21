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
            ResultSet resultSetCourse;



            arrayListEmployee.add(new Employee());
            arrayListEmployee.add(new Employee());


            while (resultSetEmployee.next()) {

                int employee_id = resultSetEmployee.getInt("employee_id");

                String employee_name = resultSetEmployee.getString("employee_name");
                System.out.println(employee_name);

                int pc5Employee = resultSetEmployee.getInt("employee_pc5");
                int pc6Employee = resultSetEmployee.getInt("employee_pc6");
                int pc15Employee = resultSetEmployee.getInt("employee_pc15");


                resultSetCourse = statement.executeQuery("SELECT * FROM course");

                while (resultSetCourse.next()) {

                    int course_id = resultSetCourse.getInt("course_id");

                    String course_name = resultSetCourse.getString("course_name");
                    System.out.println(course_name);

                    int pc5StartCourse = resultSetCourse.getInt("course_pc5_start");
                    int pc6StartCourse = resultSetCourse.getInt("course_pc6_start");
                    int pc15StartCourse = resultSetCourse.getInt("course_pc15_start");

                    int pc5EndCourse = resultSetCourse.getInt("course_pc5_end");
                    int pc6EndCourse = resultSetCourse.getInt("course_pc6_end");
                    int pc15EndCourse = resultSetCourse.getInt("course_pc15_end");

                    int price = resultSetCourse.getInt("course_price");

                    double effect = MathCompetence.getEffect(pc5Employee, pc6Employee, pc15Employee,
                            pc5StartCourse, pc6StartCourse, pc15StartCourse,
                            pc5EndCourse, pc6EndCourse, pc15EndCourse,
                            price);
                    System.out.println("ПОЛУЧЕН ЭФФЕКТ: " + effect);

                }

            }










        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
