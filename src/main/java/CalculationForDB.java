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

    private static int budget = 0;
    private static int numberOnCourse = 0;
    private static int number = 0;


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

                    String course_name = resultSetCourse.getString("course_name"); // fixme later
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


                    System.out.println("ПЕРВЫЙ РЕЗУЛЬТАТ");
                    System.out.println("ПОЛУЧЕН ЭФФЕКТ: " + String.format("%.2f", effect));

                    if (countEffect == 0) {
                        insertEffect(dbWorker);
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

    private static void insertEffect(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatementInsertEffect = dbWorker.getConnection()
                .prepareStatement("INSERT INTO effect(course_id, employee_id, effect) " +
                        "VALUE (?,?,?)");

        preparedStatementInsertEffect.setInt(1, course_id);
        preparedStatementInsertEffect.setInt(2, employee_id);
        preparedStatementInsertEffect.setDouble(3, effect);
        preparedStatementInsertEffect.executeUpdate();
    }

    private static void updateEffect(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatement = dbWorker.getConnection()
                .prepareStatement("UPDATE effect SET effect.effect = ? WHERE course_id = ? AND employee_id = ?");
        preparedStatement.setDouble(1, effect);
        preparedStatement.setInt(2, course_id);
        preparedStatement.setInt(3, employee_id);
        preparedStatement.executeUpdate();
    }

    private static void insertEmployeeEnd(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatementInsertEmployeeEnd = dbWorker.getConnection()
                .prepareStatement("INSERT INTO employee_end(employee_id, employee_pc5, employee_pc6, employee_pc15) " +
                        "VALUE (?,?,?,?)");
        preparedStatementInsertEmployeeEnd.setInt(1, employee_id);
        preparedStatementInsertEmployeeEnd.setInt(2, pc5Employee);
        preparedStatementInsertEmployeeEnd.setInt(3, pc6Employee);
        preparedStatementInsertEmployeeEnd.setInt(4, pc15Employee);
        preparedStatementInsertEmployeeEnd.executeUpdate();
    }

    private static void insertVisitation(DBWorker dbWorker) throws SQLException {
        PreparedStatement preparedStatementInsertVisitation = dbWorker.getConnection()
                .prepareStatement("INSERT INTO visitation(course_id, employee_id, visitation_order) " +
                        "VALUE (?,?,?)");
        preparedStatementInsertVisitation.setInt(1, course_id);
        preparedStatementInsertVisitation.setInt(2, employee_id);
        preparedStatementInsertVisitation.setInt(3, number + 1);
        preparedStatementInsertVisitation.executeUpdate();
    }

    public static void planMaker(DBWorker dbWorker, int maxBudget, int maxNumberOnCourse, int maxNumber) {
        try {
            while (budget < maxBudget & numberOnCourse < maxNumberOnCourse & number < maxNumber) {

                Statement statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxEffect = statement.executeQuery("SELECT effect.course_id, effect.employee_id, MAX(effect) " +
                        "FROM effect WHERE effect = (SELECT MAX(effect) FROM effect)");

                resultSetMaxEffect.next();
                course_id = resultSetMaxEffect.getInt("course_id");
                employee_id = resultSetMaxEffect.getInt("employee_id");
                effect = resultSetMaxEffect.getDouble("MAX(effect)");

                if (effect == 0) {
                    System.out.println("Максимальный эффект равен 0");
                    return;
                }

                String queryMaxCourse = "SELECT course_pc5_end, course_pc6_end, course_pc15_end, course_price " +
                        "FROM course WHERE course_id = " + course_id;
                statement = dbWorker.getConnection().createStatement();
                ResultSet resultSetMaxCourse = statement.executeQuery(queryMaxCourse);

                resultSetMaxCourse.next();
                pc5EndCourse = resultSetMaxCourse.getInt("course_pc5_end");
                pc6EndCourse = resultSetMaxCourse.getInt("course_pc6_end");
                pc15EndCourse = resultSetMaxCourse.getInt("course_pc15_end");

                price = resultSetMaxCourse.getInt("course_price");
                budget = budget + price;

                pc5Employee = pc5EndCourse;
                pc6Employee = pc6EndCourse;
                pc15Employee = pc15EndCourse;

                insertEmployeeEnd(dbWorker);
                insertVisitation(dbWorker);


                ResultSet resultSetCourse = statement.executeQuery("SELECT * FROM course");
                while (resultSetCourse.next()) {

                    course_id = resultSetCourse.getInt("course_id");

                    String course_name = resultSetCourse.getString("course_name"); // fixme later

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

                    updateEffect(dbWorker);
                }

                numberOnCourse++;
                number++;

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


}
