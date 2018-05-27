import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CalculationForDB {
    private static int employee_id;
    private static int course_id;

    private static int pc5Employee;
    private static int pc6Employee;
    private static int pc15Employee;

    private static int pc5StartCourse;
    private static int pc6StartCourse;
    private static int pc15StartCourse;

    private static int pc5EndCourse;
    private static int pc6EndCourse;
    private static int pc15EndCourse;


    private static int price;
    private static double effect;


    public static void theFirstCalculationEffect(DBWorker dbWorker) {
        try {
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetEmployee = statement.executeQuery("SELECT * FROM employee_start");

            statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetCountEffect = statement.executeQuery("SELECT COUNT(*) FROM effect");
            resultSetCountEffect.next();
            int countEffect = resultSetCountEffect.getInt(1);
            System.out.println("Число строк в эффекте: " + countEffect);

            statement = dbWorker.getConnection().createStatement();
            ResultSet resultSetCourse;

            while (resultSetEmployee.next()) {

                employee_id = resultSetEmployee.getInt("employee_id");

                String employee_name = resultSetEmployee.getString("employee_name");
                System.out.println(employee_name);

                pc5Employee = resultSetEmployee.getInt("employee_pc5");
                pc6Employee = resultSetEmployee.getInt("employee_pc6");
                pc15Employee = resultSetEmployee.getInt("employee_pc15");

                resultSetCourse = statement.executeQuery("SELECT * FROM course");

                while (resultSetCourse.next()) {

                    course_id = resultSetCourse.getInt("course_id");

                    String course_name = resultSetCourse.getString("course_name");
                    System.out.println(course_name);

                    pc5StartCourse = resultSetCourse.getInt("course_pc5_start");
                    pc6StartCourse = resultSetCourse.getInt("course_pc6_start");
                    pc15StartCourse = resultSetCourse.getInt("course_pc15_start");

                    pc5EndCourse = resultSetCourse.getInt("course_pc5_end");
                    pc6EndCourse = resultSetCourse.getInt("course_pc6_end");
                    pc15EndCourse = resultSetCourse.getInt("course_pc15_end");

                    price = resultSetCourse.getInt("course_price");

                    effect = MathCompetence.getEffect(pc5Employee, pc6Employee, pc15Employee,
                            pc5StartCourse, pc6StartCourse, pc15StartCourse,
                            pc5EndCourse, pc6EndCourse, pc15EndCourse,
                            price);


                    System.out.println("ПОЛУЧЕН ЭФФЕКТ: " + String.format("%.2f", effect));

                    if (countEffect == 0) {
                        theSecondInsertEffect(dbWorker);
                    }


                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbWorker.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void theSecondInsertEffect(DBWorker dbWorker) throws SQLException {

        PreparedStatement preparedStatementInsertEffect = dbWorker.getConnection()
                .prepareStatement("INSERT INTO effect(course_id, employee_id, effect) " +
                        "VALUE (?,?,?)");

        preparedStatementInsertEffect.setInt(1, course_id);
        preparedStatementInsertEffect.setInt(2, employee_id);
        preparedStatementInsertEffect.setDouble(3, effect);
        preparedStatementInsertEffect.executeUpdate();


    }

}
